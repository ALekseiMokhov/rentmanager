package com.senla.carservice.view;


import com.senla.carservice.controller.JsonController;
import com.senla.carservice.spring.config.AppConfig;
import com.senla.carservice.util.warning.Supressor;
import com.senla.carservice.view.menu.MenuController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        Supressor.disableWarning();


        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class );
        System.out.println( "Beans in context are: " );
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println( beanName );
        }

        JsonController jsonController =
                context.getBean( JsonController.class );
        jsonController.loadFromJson();

        MenuController menuController = context.getBean( MenuController.class );
        menuController.run();

    }
}
