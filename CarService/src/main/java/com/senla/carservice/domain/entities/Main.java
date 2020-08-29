package com.senla.carservice.domain.entities;


import com.senla.carservice.controller.JsonController;
import com.senla.carservice.controller.MenuController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import property.configurer.PropertyLoader;
import spring.config.AppConfig;
import util.warning.Supressor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        Supressor.disableWarning();

        PropertyLoader.loadDefaultProperties();
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        System.out.println( "Beans in context are: " );
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println( beanName );
        }

        JsonController jsonController =
                (JsonController) context.getBean( "jsonController" );
        jsonController.loadFromJson();
        MenuController menuController = (MenuController) context.getBean( "menuController" );
        menuController.run();
        if (menuController.getBuilder().getIsJsonPersistenceAllowed()) {

            jsonController.exportToJson();
        }
    }
}
