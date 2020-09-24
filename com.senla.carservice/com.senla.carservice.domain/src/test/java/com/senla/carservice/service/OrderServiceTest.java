package com.senla.carservice.service;

import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.entity.master.AbstractMaster;
import com.senla.carservice.entity.master.Painter;
import com.senla.carservice.entity.master.Reshaper;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.entity.order.Order;
import com.senla.carservice.entity.order.OrderStatus;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.repository.jpa.OrderJpaRepository;
import com.senla.carservice.util.calendar.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private IGenericRepository mockRepo = Mockito.mock( OrderJpaRepository.class );

    @Mock
    private MasterService mockMasterService;
    @Mock
    private PlaceService mockPlaceService;

    @InjectMocks
    private OrderService orderService;

    private Place place;
    private AbstractMaster reshaper;
    private AbstractMaster painter;
    private List <AbstractMaster> masterList;
    private List <AbstractMaster> expensiveMasters;
    private List <Order> orderList;
    private Set <Speciality> required;
    private Set <Speciality> requiredExpensive;


    private Order testOrder;
    private Order expensiveOrder;
    private UUID id;
    private UUID idExp;

    @BeforeEach
    void init() {

        place = new Place( new Calendar() );
        reshaper = new Reshaper( "Sergei", 3.6, new Calendar(), Speciality.RESHAPER );
        painter = new Painter( "Andrew", 22, new Calendar(), Speciality.PAINTER );
        masterList = new ArrayList <>();
        masterList.add( reshaper );

        orderList = new ArrayList <>();
        required = new HashSet <>();
        required.add( Speciality.RESHAPER );

        testOrder = new Order( LocalDate.now(), LocalDate.of( 2020, 9, 23 ), place, masterList );
        id = testOrder.getId();

        expensiveMasters = new ArrayList <>();
        expensiveMasters.add( reshaper );
        expensiveMasters.add( painter );

        requiredExpensive = new HashSet <>();
        requiredExpensive.add( Speciality.RESHAPER );
        requiredExpensive.add( Speciality.PAINTER );

        expensiveOrder = new Order( LocalDate.now(), LocalDate.of( 2040, 4, 21 ), place, expensiveMasters );
        idExp = expensiveOrder.getId();

        orderList.add( expensiveOrder );
        orderList.add( testOrder );

        doNothing().when( mockRepo ).save( any( Order.class ) );
        when( mockRepo.getById( id ) ).thenReturn( testOrder );
        when( mockRepo.getById( idExp ) ).thenReturn( expensiveOrder );
        when( mockRepo.findAll() ).thenReturn( orderList );


    }

    @Test
    void addOrder() {
        when( mockPlaceService.getFreePlace( any( LocalDate.class ) ) ).thenReturn( place );
        when( mockMasterService.getFreeBySpeciality( any( LocalDate.class ), any( Speciality.class ) ) ).thenReturn( reshaper );
        this.orderService.addOrder( LocalDate.now(), LocalDate.of( 2020, 9, 24 ), required );
        verify( mockRepo, times( 1 ) ).save( any( Order.class ) );
        verify( mockPlaceService, times( 1 ) ).getFreePlace( LocalDate.of( 2020, 9, 24 ) );
        verify( mockMasterService, times( required.size() ) ).getFreeBySpeciality( any( LocalDate.class ), any( Speciality.class ) );
    }

    @Test
    void saveOrder() {
        this.orderService.saveOrder( testOrder );
        verify( mockRepo, times( 1 ) ).save( testOrder );

    }


    @Test
    void deleteOrder() {
        this.orderService.deleteOrder( id );
        verify( mockRepo, times( 1 ) ).delete( id );
    }

    @Test
    void shiftOrderExecutionDate() {
        when( mockPlaceService.getFreePlace( any( LocalDate.class ) ) ).thenReturn( place );
        when( mockMasterService.getFreeBySpeciality( any( LocalDate.class ), any( Speciality.class ) ) ).thenReturn( reshaper );
        doNothing().when( mockMasterService ).setMasterForDate( any( UUID.class ), any( LocalDate.class ) );

        LocalDate newDate = LocalDate.of( 2030, 12, 31 );
        this.orderService.shiftOrderExecutionDate( id, newDate );

        verify( mockPlaceService, times( 1 ) ).getFreePlace( newDate );
        verify( mockMasterService, times( required.size() ) ).getFreeBySpeciality( newDate, Speciality.RESHAPER );

        InOrder inOrder = inOrder( mockPlaceService, mockMasterService, mockRepo );
        inOrder.verify( mockRepo ).getById( id );
        inOrder.verify( mockPlaceService ).getFreePlace( newDate );
        inOrder.verify( mockMasterService ).getFreeBySpeciality( newDate, Speciality.RESHAPER );
        inOrder.verify( mockMasterService ).setMasterForDate( any( UUID.class ), any( LocalDate.class ) );


        Assertions.assertEquals( this.orderService.findOrderById( id ).getStartOfExecution(), newDate );
    }

    @Test
    void setNewMasters() {
        when( mockMasterService.getFreeBySpeciality( any( LocalDate.class ), any( Speciality.class ) ) ).thenReturn( reshaper );
        this.orderService.setNewMasters( id );
        InOrder inOrder = inOrder( mockMasterService, mockRepo );
        inOrder.verify( mockRepo ).getById( id );
        inOrder.verify( mockMasterService, times( required.size() ) ).getFreeBySpeciality( any( LocalDate.class ), any( Speciality.class ) );
    }

    @Test
    void cancelOrder() {
        this.orderService.cancelOrder( id );
        Assertions.assertEquals( this.orderService.findOrderById( id ).getStatus(), OrderStatus.CANCELLED );
    }

    @Test
    void completeOrder() {
        this.orderService.completeOrder( id );
        Assertions.assertNotEquals( this.orderService.findOrderById( id ).getStatus(), OrderStatus.CANCELLED );
    }


    @Test
    void getOrdersByPrice() {
        List <Order> result = this.orderService.getOrdersByPrice( OrderStatus.MANAGED );

        Assertions.assertEquals( 2, result.size() );
        Assertions.assertEquals( 25.6, result.get( 1 ).getTotalPrice() );
    }

    @Test
    void getOrdersByBookedDate() {
        List <Order> result = this.orderService.getOrdersByBookedDate( OrderStatus.MANAGED );
        Assertions.assertEquals( LocalDate.of( 2040, 4, 21 ), result.get( 0 ).getStartOfExecution() );
    }


    @Test
    void getOrdersForPeriod() {
        this.orderService.completeOrder( id );
        this.orderService.completeOrder( idExp );
        List <Order> result = this.orderService.getOrdersForPeriod( LocalDate.of( 2020, 1, 1 ), LocalDate.of( 2100, 1, 1 ) );
        Assertions.assertEquals( 2, result.size() );
    }
}
