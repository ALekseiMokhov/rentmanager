package properties.configurer;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import properties.configurer.annotations.ConfigProperty;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Collectors;

public class ConfigPropertyScanner {


    public static Set <Class> classesWithPropertiesToInject(String pakcage) {
        Reflections reflections = new Reflections( pakcage, new TypeAnnotationsScanner(), new SubTypesScanner(), new FieldAnnotationsScanner() );

        return reflections.getFieldsAnnotatedWith( ConfigProperty.class ).stream()
                .map( f -> f.getType() )
                .collect( Collectors.toSet() );
    }


    public static boolean hasPropertiesToInject(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent( ConfigProperty.class )) {
                return true;
            }
        }
        return false;
    }


}
