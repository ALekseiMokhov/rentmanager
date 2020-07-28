package property.configurer;

import dependency.injection.annotations.components.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import property.configurer.annotations.ConfigProperty;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;

@Component
public class PropertyInjector {
    private static final Logger LOGGER = LoggerFactory.getLogger( PropertyInjector.class );

    private Properties properties = PropertyStorage.getCachedProperties();


    public void injectProperty(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();


        Arrays.stream( fields )
                .forEach( f -> f.setAccessible( true ) );
        Arrays.stream( fields )
                .filter( f -> f.isAnnotationPresent( ConfigProperty.class ) )
                .forEach( f -> {
                    try {
                        inject( object, f, f.getAnnotation( ConfigProperty.class ).propertyName() );

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } );


    }

    private void inject(Object object, Field field, String propertyName) throws IllegalAccessException {
        String propValue = properties.getProperty( propertyName );
        LOGGER.info( field + " " + propertyName + " " );
        if (field.getType().isAssignableFrom( Boolean.class ) || field.getType().isAssignableFrom( boolean.class )) {
            field.set( object, Boolean.valueOf( propValue ) );
        }
        if (field.getType().isAssignableFrom( Integer.class ) || field.getType().isAssignableFrom( int.class )) {
            field.set( object, Integer.valueOf( properties.getProperty( propertyName ) ) );
        }
        if (field.getType().isAssignableFrom( Double.class ) || field.getType().isAssignableFrom( double.class )) {
            field.set( object, Double.valueOf( properties.getProperty( propertyName ) ) );
        }

        if (field.getType().isAssignableFrom( Long.class ) || field.getType().isAssignableFrom( long.class )) {
            field.set( object, Long.valueOf( properties.getProperty( propertyName ) ) );
        }
        if (field.getType().isAssignableFrom( String.class )) {
            field.set( object, properties.getProperty( propertyName ) );
        }


    }


}
