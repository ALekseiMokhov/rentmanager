import main.entities.garage.Place;
import main.entities.master.Master;
import main.entities.master.Painter;
import main.entities.master.Reshaper;
import main.entities.order.Order;
import main.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestOrderService {
    private OrderService service;

    @BeforeEach
    void init() {
        service = new OrderService();
        List <Master> masters = Arrays.asList( new Reshaper( "Andrew", 2.3 ), new Painter( "John", 4.4 ) );
        service.addOrder( LocalDate.of( 2020, 07, 01 )
                , LocalDate.of( 2020, 07, 02 ), masters, new Place() );
        service.addOrder( LocalDate.of( 2020, 07, 01 )
                , LocalDate.of( 2020, 07, 03 ), masters, new Place() );
        service.addOrder( LocalDate.of( 2020, 07, 01 )
                , LocalDate.of( 2020, 07, 04 ), masters, new Place() );
        for (Order order : service.getOrders()) {
            System.out.println( "generated order id is: " + order.getId() );
        }

    }

    @Test
    void testFindOrderById() {
        UUID id = service.getOrders().get( 0 ).getId();
        Assertions.assertNotNull( service.findOrderById( id ) );
    }

    @Test
    void testShiftOrdersDate() {
        for (Order order : service.getOrders()) {
            System.out.println( "Old date :" + order.getStartOfExecution() );
        }
        service.shiftOrderExecutionDate( service.getOrders().get( 1 ), LocalDate.of( 2020, 8, 01 ) );
        for (Order order : service.getOrders()) {
            System.out.println( "New date :" + order.getStartOfExecution() );
        }
    }

    @Test
    void testSetNewMaster() {
        List <Master> masters = service.getOrders().get( 2 ).getMasters();
        for (int i = 0; i < masters.size(); i++) {
            service.setNewMaster( service.getOrders().get( 2 ), masters.get( i ), new Painter( "Basil" + i, 1.1 ) );
        }

    }
}
