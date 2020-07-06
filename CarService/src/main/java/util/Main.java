package util;


import com.senla.carservice.controller.OrderController;
import com.senla.carservice.domain.service.IOrderService;
import com.senla.carservice.view.menu.MenuController;
import dependency.injection.beanfactory.BeanFactory;
import util.warning.Supressor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        Supressor.disableWarning();

       /* MenuController controller = new MenuController();
        controller.run();
*/
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.loadMetadata( "com.senla.carservice" );
        beanFactory.instantiate( "com.senla.carservice" );
        beanFactory.injectDependencies();
        
    }
}
