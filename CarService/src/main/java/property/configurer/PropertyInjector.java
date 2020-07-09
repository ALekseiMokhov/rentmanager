package property.configurer;

import dependency.injection.annotations.components.Component;
import property.configurer.annotations.ConfigProperty;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PropertyInjector {

    private Properties properties = PropertyStorage.getCachedProperties();


    public void injectProperty(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();

        /*store accessability of each field*/
        Map <Field, Boolean> accessModeMap = Stream.of( fields )
                .collect( Collectors.toMap( f -> f, f -> f.isAccessible() ) );
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
        /*restore accessability of each field*/

        Arrays.stream( fields )
                .forEach( f -> f.setAccessible( accessModeMap.get( f ) ) );

    }

    private void inject(Object object, Field field, String propertyName) throws IllegalAccessException {
        String propValue = properties.getProperty( propertyName );

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


    }


}
