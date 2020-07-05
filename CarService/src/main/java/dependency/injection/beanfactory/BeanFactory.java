package dependency.injection.beanfactory;


import dependency.injection.annotations.Autowired;
import dependency.injection.annotations.Qualifier;
import dependency.injection.annotations.components.Component;
import dependency.injection.beanfactory.exceptions.BeanInitException;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class BeanFactory {
    private final HashMap <Class, List <Class>> metaData = new HashMap <>();
    private final HashMap <String, Object> singletons = new HashMap <>();
    private final HashSet <String> prototypeNames = new HashSet <>();

    /*loading Class type of singletons and fields to inject*/
    public void loadMetadata(String pakcage) throws NoSuchMethodException {
        Reflections reflections = new Reflections( pakcage,new TypeAnnotationsScanner(),new SubTypesScanner(), new FieldAnnotationsScanner() );

        Set <Class <?>> allBeanTypes = reflections.getTypesAnnotatedWith( Component.class );


        for (Class <?> beanType : allBeanTypes) {
            List <Class> fieldsToInject = new ArrayList <>();
            Field[]fields = beanType.getDeclaredFields();
            Arrays.stream( fields )
                    .forEach( f->f.setAccessible( true ) );

            fieldsToInject.addAll( Arrays.stream( fields )
                    .filter( f->f.isAnnotationPresent( Autowired.class ) )
                    .map( f->f.getType() )
                    .collect( Collectors.toList()) )  ;
            metaData.put( beanType,fieldsToInject ) ;
        }

    }

    public void instantiate(String pakcage) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        for (Class aClass : metaData.keySet()) {
             singletons.put( aClass.getName(),aClass.isInterface()? findImplementation(aClass): aClass.newInstance()   ) ;
            System.out.println(singletons.entrySet());
        }

    }


    public Object getSingleton(String beanName) {
        return singletons.get( beanName );
    }

    public Object getPrototype(String beanName) {
        if (!prototypeNames.contains( beanName )) {
            throw new BeanInitException();
        }
        return null;
    }


    private  Object findImplementation(Class  clazz) throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections( clazz.getPackageName(),new TypeAnnotationsScanner(),new SubTypesScanner());
         List <Class <?>> implementations=  new ArrayList <>(reflections.getSubTypesOf(clazz  ));
         if(implementations.size() == 1)  {

             return implementations.get( 0 ) .newInstance();
         }
        for (Class  implementation : implementations) {

           if(implementation.isAnnotationPresent( Qualifier.class )) {
               return implementation.newInstance();
           }
        }
        return null;
    }


}
