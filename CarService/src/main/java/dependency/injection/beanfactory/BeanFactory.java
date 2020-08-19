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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;


public class BeanFactory {
    private static final BeanFactory INSTANCE = new BeanFactory();
    private static final Logger LOGGER = LoggerFactory.getLogger( BeanFactory.class.getClass() );
    private final HashMap <Class, List <Class>> metaData = new HashMap <>();
    private final HashMap <String, Object> singletons = new HashMap <>();
    private final HashSet <String> prototypeNames = new HashSet <>();

    private BeanFactory() {

    }

    public static BeanFactory getInstance() {
        return INSTANCE;
    }

    /*loading Class type of singletons and fields to inject*/
    public void loadMetadata(String pakcage) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections( pakcage, new TypeAnnotationsScanner()
                , new SubTypesScanner(), new FieldAnnotationsScanner() );

        Set <Class <?>> allBeanTypes =new CopyOnWriteArraySet <>();
        allBeanTypes.addAll( reflections.getTypesAnnotatedWith( Component.class ) );
        
        for (Class <?> allBeanType : allBeanTypes) {
          if(allBeanType.isAnnotationPresent( Deprecated.class ))  {
              allBeanTypes.remove( allBeanType ) ;/**/
          }
        }

        LOGGER.info( "Beans found: " + allBeanTypes.size() + " : " + allBeanTypes.stream().collect( Collectors.toList() ) );

        /*finding implementation for interface*/
        for (Class <?> clazz : List.copyOf( allBeanTypes )) {
            if (clazz.isInterface()) {
                Class impl = findImplementationClass( clazz );
                allBeanTypes.add( impl );
                allBeanTypes.remove( clazz );
            }
        }


        for (Class <?> beanType : allBeanTypes) {
            List <Class> fieldsToInject = new ArrayList <>();
            Field[] fields = beanType.getDeclaredFields();


            Arrays.stream( fields )
                    .forEach( f -> f.setAccessible( true ) );

            fieldsToInject.addAll( Arrays.stream( fields )
                    .filter( f -> f.isAnnotationPresent( Autowired.class ) )
                    .map( f -> f.getType() )
                    .collect( Collectors.toList() ) );

            metaData.put( beanType, fieldsToInject );
        }

    }

    /*create beans with null autowired fields*/
    public void instantiate(String pakcage) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        for (Class clazz : metaData.keySet()) {
            singletons.put( formatBeanName( clazz.getSimpleName() ), clazz.newInstance() );

        }

        LOGGER.info( "Beans loaded: " + metaData.keySet().size() + ":" + metaData.keySet() );
    }

    /*inject bean dependencies*/
    public void injectDependencies() throws IllegalAccessException {
        for (Class clazz : metaData.keySet()) {
            for (Class injecting : metaData.get( clazz )) {
                Object bean = singletons.get( formatBeanName( clazz.getSimpleName() ) );

                List <Field> fieldsToInject = Arrays.asList( bean.getClass().getDeclaredFields() ).stream()
                        .filter( f -> f.isAnnotationPresent( Autowired.class ) )
                        .collect( Collectors.toList() );


                for (Field field : fieldsToInject) {
                    field.setAccessible( true );
                    Class <?> type = field.getType();


                    Object injectedBean = singletons.values().stream()
                            .filter( v -> type.isAssignableFrom( v.getClass() ) )
                            .findFirst().get();


                    field.set( bean, injectedBean );

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

    public ArrayList <Object> getSingletons() {
        ArrayList <Object> copyBeans = new ArrayList <>();
        copyBeans.addAll( singletons.values() );
        return copyBeans;
    }

    private Class findImplementationClass(Class clazz) throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections( clazz.getPackageName(), new TypeAnnotationsScanner(), new SubTypesScanner() );
        List <Class <?>> implementations = new ArrayList <>( reflections.getSubTypesOf( clazz ) );

        for (Class implementation : implementations) {

            if (implementation.isAnnotationPresent( Qualifier.class )) {


                return implementation;
            }
        }
        if (implementations.size() == 1) {

            return implementations.get( 0 );
        }
        throw new BeanCollisionException();
    }

    private String formatBeanName(String input) {
        return input.toLowerCase();
    }


}
