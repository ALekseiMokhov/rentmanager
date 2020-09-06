package properties.configurer;

import lombok.extern.slf4j.Slf4j;
import properties.configurer.annotations.ConfigProperty;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
public class PropertyInjector {

    private static final Properties PROPERTIES = PropertyStorage.getCachedProperties();


    public static void injectProperty(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();


        Arrays.stream( fields )
                .forEach( f -> f.setAccessible( true ) );
        Arrays.stream( fields )
                .filter( f -> f.isAnnotationPresent( ConfigProperty.class ) )
                .forEach( f -> {
                    try {
                        inject( object, f, f.getAnnotation( ConfigProperty.class ).propertyName() );

                    } catch (IllegalAccessException e) {
                        log.error( e.getMessage() );
                    }
                } );


    }

    private static void inject(Object object, Field field, String propertyName) throws IllegalAccessException {
        String propValue = PROPERTIES.getProperty( propertyName );
        log.info( field + " " + propertyName + " " + "were injected" );
        if (field.getType().isAssignableFrom( Boolean.class ) || field.getType().isAssignableFrom( boolean.class )) {
            field.set( object, Boolean.valueOf( propValue ) );
        }
        if (field.getType().isAssignableFrom( Integer.class ) || field.getType().isAssignableFrom( int.class )) {
            field.set( object, Integer.valueOf( PROPERTIES.getProperty( propertyName ) ) );
        }
        if (field.getType().isAssignableFrom( Double.class ) || field.getType().isAssignableFrom( double.class )) {
            field.set( object, Double.valueOf( PROPERTIES.getProperty( propertyName ) ) );
        }

        if (field.getType().isAssignableFrom( Long.class ) || field.getType().isAssignableFrom( long.class )) {
            field.set( object, Long.valueOf( PROPERTIES.getProperty( propertyName ) ) );
        }
        if (field.getType().isAssignableFrom( String.class )) {
            field.set( object, PROPERTIES.getProperty( propertyName ) );
        }


    }


}
