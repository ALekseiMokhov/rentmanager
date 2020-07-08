import com.senla.carservice.controller.OrderController;
import com.senla.carservice.domain.service.IOrderService;
import dependency.injection.beanfactory.BeanFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class TestBeanFactory {
    BeanFactory beanFactory;

    @BeforeEach
    void init() throws NoSuchMethodException, IllegalAccessException, InstantiationException, IOException, ClassNotFoundException {
        beanFactory = new BeanFactory();
        beanFactory.loadMetadata( "com.senla.carservice" );
        beanFactory.instantiate( "com.senla.carservice" );
    }

    @Test
    void getSingletons() throws NoSuchMethodException, IllegalAccessException, InstantiationException, NoSuchFieldException {

        Field field = beanFactory.getClass().getDeclaredField( "metaData" );
        field.setAccessible( true );
        HashMap <String, Object> testMap = (HashMap <String, Object>) field.get( beanFactory );
        Assertions.assertEquals( 11, testMap.keySet().size() );
    }

    @Test
    void testIsSingleton() {

        OrderController orderController = (OrderController) beanFactory.getSingleton( "ordercontroller" );
        OrderController orderController1 = (OrderController) beanFactory.getSingleton( "ordercontroller" );
        Assertions.assertNotEquals( null, orderController );
        Assertions.assertEquals( orderController, orderController1 );
    }

    @Test
    void testInjection() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        beanFactory.injectDependencies();
        OrderController orderController = (OrderController) beanFactory.getSingleton( "ordercontroller" );
        Field field = orderController.getClass().getDeclaredField( "orderService" );
        field.setAccessible( true );
        IOrderService orderService = (IOrderService) field.get( orderController );
        Assertions.assertNotEquals( null, orderService );

    }

}
