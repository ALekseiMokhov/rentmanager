package property.configurer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ConfigProperty {
    String configName() default "config.properties";

    String propertyName();

    Class type() default String.class;
    /*Class type() default String.class;*/
}
