import main.entities.garage.Place;
import main.entities.master.*;
import main.entities.order.Order;
import main.entities.order.OrderStatus;
import main.repository.MasterRepository;
import main.repository.OrderRepository;
import main.repository.PlaceRepository;
import main.service.MasterService;
import main.service.OrderService;
import main.service.PlaceService;
import main.util.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

public class TestOrderService {
    private OrderService orderService;
    private OrderRepository orderRepository;
    private MasterService masterService;
    private PlaceService placeService;

    @BeforeEach
    void init() {
        this.orderRepository = new OrderRepository();
        this.masterService = new MasterService( new MasterRepository() );
        this.placeService = new PlaceService( new PlaceRepository() );
        this.orderService = new OrderService( orderRepository, masterService, placeService );

        List <Master> masters = Arrays.asList
                ( new Reshaper( "Andrew", 2.3, new Calendar(), Speciality.RESHAPER ),
                        new Painter( "John", 4.4, new Calendar(), Speciality.PAINTER ),
                        new Electrician( "Fred", 1.4, new Calendar(), Speciality.ELECTRICIAN ),
                        new Mechanic( "Joe", 4.6, new Calendar(), Speciality.MECHANIC ),
                        new Reshaper( "AAA", 1.1, new Calendar(), Speciality.RESHAPER ),
                        new Painter( "BBB", 2.2, new Calendar(), Speciality.PAINTER ),
                        new Electrician( "CCC", 3.3, new Calendar(), Speciality.ELECTRICIAN ),
                        new Mechanic( "DDD", 4.4, new Calendar(), Speciality.MECHANIC ) );
        for (Master master : masters) {
            this.masterService.saveMaster( master );
        }

        Set <Speciality> specialitySet = new HashSet <>( Arrays.asList( Speciality.values() ) );

        for (int i = 0; i < 10; i++) {
            this.placeService.savePlace( new Place( new Calendar() ) );
        }

        orderService.addOrder( LocalDate.of( 2020, 07, 01 )
                , LocalDate.of( 2020, 07, 02 ), specialitySet );
        orderService.addOrder( LocalDate.of( 2020, 02, 01 )
                , LocalDate.of( 2020, 07, 03 ), specialitySet );
        orderService.addOrder( LocalDate.of( 2020, 07, 01 )
                , LocalDate.of( 2020, 07, 04 ), specialitySet );
        orderService.addOrder( LocalDate.of( 2020, 07, 01 )
                , LocalDate.of( 2020, 07, 05 ), specialitySet );
        orderService.addOrder( LocalDate.of( 2020, 07, 01 )
                , LocalDate.of( 2020, 07, 06 ), specialitySet );
        orderService.addOrder( LocalDate.of( 2020, 07, 01 )
                , LocalDate.of( 2020, 07, 07 ), specialitySet );



    }

    @Test
    void testFindOrderById() {
        System.out.println("Test findOrderById:");
        UUID id = orderService.getOrders().get( 0 ).getId();
        Assertions.assertNotNull( orderService.findOrderById( id ) );
        for (Order order : orderService.getOrders()) {
            System.out.println( "generated order id is: " + order.getId() );
        }
    }

    @Test
    void testShiftOrdersDate() {
        System.out.println("Test shiftOrdersDate: ");
        for (Order order : orderService.getOrders()) {
            System.out.println( "Old date :" + order.getStartOfExecution() );
        }
        orderService.shiftOrderExecutionDate( orderService.getOrders().get( 1 ), LocalDate.of( 2020, 8, 01 ) );
        for (Order order : orderService.getOrders()) {
            System.out.println( "New date :" + order.getStartOfExecution() );
        }
    }

    @Test
    void testSetNewMasters() {
        Order order = orderService.getOrders().get( 1 );
        System.out.println("Test setNewMasters: ");
        System.out.println( "Booking: " + order.getDateBooked() );
        System.out.println( "Start: " + order.getStartOfExecution() );
        System.out.println( "Finish: " + order.getFinishOfExecution() );
        List <Master> old = order.getMasters();

        for (Master master : old) {
            System.out.println(master);
        }
        System.out.println("_________________");
        this.orderService.setNewMasters( order );
        List<Master> current = this.orderService.getOrders().get( 1 ).getMasters();
        for (Master master : current) {
            System.out.println(master);
        }
        Assertions.assertNotEquals(old.get(0  ),current.get( 0 ));
        Assertions.assertNotEquals(old.get(1  ),current.get( 1 ));
        Assertions.assertNotEquals(old.get(2  ),current.get( 2 ));
        Assertions.assertNotEquals(old.get(3  ),current.get( 3 ));

    }
    @Test
    void testCancelOrder(){
        Order orderToCancel = this.orderService.getOrdersByBookedDate( OrderStatus.MANAGED ).get( 0 );
        UUID id = orderToCancel.getId();
        this.orderService.cancelOrder( id );
        Assertions.assertEquals( this.orderService.findOrderById( id ).getStatus(), OrderStatus.CANCELLED);
    }
}
