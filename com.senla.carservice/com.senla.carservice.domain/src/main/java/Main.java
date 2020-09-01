import com.senla.carservice.controller.IMenuController;
import com.senla.carservice.controller.JsonController;
import com.senla.carservice.properties.configurer.PropertyLoader;
import com.senla.carservice.spring.config.AppConfig;
import com.senla.carservice.util.warning.Supressor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        Supressor.disableWarning();
        PropertyLoader.loadCustomProperties( "/application.properties" );

        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        System.out.println( "Beans in context are: " );
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println( beanName );
        }

        JsonController jsonController =
                (JsonController) context.getBean( "jsonController" );
        jsonController.loadFromJson();

        IMenuController menuController = (IMenuController) context.getBean( "menuController" );
        menuController.setApplicationContext( context );
        menuController.run();

    }
}
