package dependency.injection.beanfactory;


import dependency.injection.annotations.Autowired;
import dependency.injection.annotations.Qualifier;
import dependency.injection.annotations.components.Component;
import dependency.injection.beanfactory.exceptions.BeanCollisionException;
import dependency.injection.beanfactory.exceptions.BeanInitException;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BeanFactory {
    private final HashMap <Class, List <Class>> metaData = new HashMap <>();
    private final HashMap <String, Object> singletons = new HashMap <>();
    private final HashSet <String> prototypeNames = new HashSet <>();

    /*loading Class type of singletons and fields to inject*/
    public void loadMetadata(String pakcage) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections( pakcage, new TypeAnnotationsScanner(), new SubTypesScanner(), new FieldAnnotationsScanner() );

        Set <Class <?>> allBeanTypes = reflections.getTypesAnnotatedWith( Component.class );

        /*finding implementation for interface*/
        for (Class <?> clazz : List.copyOf( allBeanTypes )) {
            if (clazz.isInterface()) {
                allBeanTypes.add( findImplementationClass( clazz ) );
                allBeanTypes.remove( clazz );
            }
        }

        for (Class <?> beanType : allBeanTypes) {
            List <Class> fieldsToInject = new ArrayList <>();
            Field[] fields = beanType.getDeclaredFields();

            /*store accessability of each field*/
            Map <Field, Boolean> accessModeMap = Stream.of( fields )
                    .collect( Collectors.toMap( f -> f, f -> f.isAccessible() ) );

            Arrays.stream( fields )
                    .forEach( f -> f.setAccessible( true ) );

            fieldsToInject.addAll( Arrays.stream( fields )
                    .filter( f -> f.isAnnotationPresent( Autowired.class ) )
                    .map( f -> f.getType() )
                    .collect( Collectors.toList() ) );
            /*restore accessability of each field*/
            Arrays.stream( fields )
                    .forEach( f -> f.setAccessible( accessModeMap.get( f ) ) );
            metaData.put( beanType, fieldsToInject );
        }

    }

    /*create beans with null autowired fields*/
    public void instantiate(String pakcage) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        for (Class clazz : metaData.keySet()) {
            singletons.put( formatBeanName( clazz.getSimpleName() ), clazz.newInstance() );

        }
    }

    /*inject bean dependencies*/
    public void injectDependencies() throws IllegalAccessException {
        for (Class clazz : metaData.keySet()) {
            for (Class injecting : metaData.get( clazz )) {
                Object bean = singletons.get( formatBeanName( clazz.getSimpleName() ) );

                List <Field> fieldsToInject = Arrays.asList( bean.getClass().getDeclaredFields() ).stream()
                        .filter( f -> f.isAnnotationPresent( Autowired.class ) )
                        .collect( Collectors.toList() );

                /*store accessability of each field*/
                Map <Field, Boolean> accessModeMap = fieldsToInject.stream()
                        .collect( Collectors.toMap( f -> f, f -> f.isAccessible() ) );

                for (Field field : fieldsToInject) {
                    field.setAccessible( true );
                    Class <?> type = field.getType();


                    Object injectedBean = singletons.values().stream()
                            .filter( v -> type.isAssignableFrom( v.getClass() ) )
                            .findFirst().get();


                    field.set( bean, injectedBean );
                    /*restore accessability of each field*/
                    fieldsToInject.stream()
                            .forEach( f -> f.setAccessible( accessModeMap.get( f ) ) );
                }

                singletons.put( formatBeanName( clazz.getSimpleName() ), bean );

            }

        }

    }

    public Object getSingleton(String beanName) {
        return singletons.get( beanName );
    }

    public Object getPrototype(String beanName) {    /**/
        if (!prototypeNames.contains( beanName )) {
            throw new BeanInitException();
        }
        return null;
    }


    private Class findImplementationClass(Class clazz) throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections( clazz.getPackageName(), new TypeAnnotationsScanner(), new SubTypesScanner() );
        List <Class <?>> implementations = new ArrayList <>( reflections.getSubTypesOf( clazz ) );
        if (implementations.size() == 1) {

            return implementations.get( 0 );
        }
        for (Class implementation : implementations) {

            if (implementation.isAnnotationPresent( Qualifier.class )) {
                return implementation;
            }
        }
        throw new BeanCollisionException();
    }

    private String formatBeanName(String input) {
        return input.toLowerCase();
    }



}
