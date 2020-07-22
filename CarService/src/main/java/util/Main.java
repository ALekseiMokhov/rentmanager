package util;


import com.senla.carservice.controller.JsonController;
import com.senla.carservice.controller.MenuController;
import dependency.injection.beanfactory.BeanFactory;
import org.apache.log4j.BasicConfigurator;
import util.warning.Supressor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        Supressor.disableWarning();
        BasicConfigurator.configure();

        BeanFactory beanFactory = new BeanFactory();
        beanFactory.loadMetadata( "com.senla.carservice" );
        beanFactory.instantiate( "com.senla.carservice" );
        beanFactory.injectDependencies();

        JsonController jsonController = (JsonController) beanFactory.getSingleton( "jsoncontroller" );
        jsonController.loadFromJson();


        MenuController menuController = new MenuController();
        menuController.run();

        jsonController.exportToJson();


    }
}
