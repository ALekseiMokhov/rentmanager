package ru.rambler.alexeimohov.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import ru.rambler.alexeimohov.dao.interfaces.OrderDao;
import ru.rambler.alexeimohov.dao.jpa.OrderDaoJpaImpl;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.VehicleDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.*;
import ru.rambler.alexeimohov.entities.Card;
import ru.rambler.alexeimohov.entities.Order;
import ru.rambler.alexeimohov.entities.User;
import ru.rambler.alexeimohov.entities.Vehicle;
import ru.rambler.alexeimohov.entities.enums.OrderStatus;
import ru.rambler.alexeimohov.entities.enums.Privilege;
import ru.rambler.alexeimohov.service.events.OrderCreatedEvent;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class TestOrderService {
    private OrderDao orderDao = Mockito.mock( OrderDaoJpaImpl.class );

    private ApplicationEventPublisher publisher = Mockito.mock( ApplicationEventPublisher.class );

    private OrderMapper orderMapper = Mockito.mock( OrderMapperImpl.class );

    private UserMapper userMapper = Mockito.mock( UserMapperImpl.class );

    private VehicleMapper vehicleMapper = Mockito.mock( VehicleMapperImpl.class );

    @InjectMocks
    private OrderService orderService;

    private OrderDto orderDto;

    private Vehicle vehicle;

    private Order order;

    private User user;

    private Card card;


    @BeforeEach
    void init() {
        this.card = new Card();
        card.setCreditCardNumber( 1111_1111_1111_1111l );
        card.setAvailableFunds( 1000_000_000 );
        
        this.user = new User();
        user.setUsername( "Alex" );
        user.setPrivilege( Privilege.NEWBIE );
        user.getCreditCards().add( card ) ;

        this.vehicle = new Vehicle();

        this.order = new Order( 342l, LocalDateTime.now(), LocalDateTime.now(), null, 1111_1111_1111_1111l,
                8.0, 0.0, false, OrderStatus.IN_RENT, user, vehicle );
        this.orderDto = new OrderDto( "1", String.valueOf( LocalDateTime.now() ),
                String.valueOf( LocalDateTime.now() ), null, "1111_1111_1111_1111l", "20",
                null, "false", "IN_RENT", new UserDto(), new VehicleDto() );

    }

    @Test
    void createOrderAndExpectConsistency() {

        //given
        given( orderMapper.fromDto( any() ) ).willReturn( order );
        given( userMapper.fromDto( any() ) ).willReturn( user );
        given( vehicleMapper.fromDto( any() ) ).willReturn( vehicle );
        //when
        order.setId( null );
        orderService.saveOrUpdate( orderDto );
        //then
        verify( orderDao, times( 1 ) ).save( order );
        verify( orderDao, never() ).update( order );
        verify( publisher, times( 1 ) ).publishEvent( any( OrderCreatedEvent.class ) );

    }

    @Test
    void updateOrderAndExpectConsistency() {

        //given
        given( orderMapper.fromDto( any() ) ).willReturn( order );
        given( userMapper.fromDto( any() ) ).willReturn( user );
        given( vehicleMapper.fromDto( any() ) ).willReturn( vehicle );
        //when
        orderService.saveOrUpdate( orderDto );
        //then
        verify( orderDao, times( 1 ) ).update( order );
        verify( orderDao, never() ).save( order );
        verify( publisher, never() ).publishEvent( any() );

    }

    @Test
    void finishAndExpectPublishingEvent() {

        //given
        given( orderDao.findById( anyLong() ) ).willReturn( order );
        InOrder inOrder = inOrder( orderDao, publisher );
        //when
        orderService.finish( anyLong() );
        //then
        inOrder.verify( orderDao ).findById( anyLong() );
        inOrder.verify( publisher ).publishEvent( any( OrderFinishedEvent.class ) );
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void cancelAndExpectConsistency() {

        //given
        given( orderDao.findById( anyLong() ) ).willReturn( order );
        InOrder inOrder = inOrder( orderDao, publisher );
        //when
        orderService.cancel( anyLong() );
        //then
        Assertions.assertEquals( 0, order.getTotalPrice() );
        inOrder.verify( orderDao ).findById( anyLong() );
        inOrder.verify( publisher ).publishEvent( any( OrderFinishedEvent.class ) );
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void countTotalPriceFail() {
        Assertions.assertThrows( IllegalArgumentException.class,
                () -> orderService.countTotalPrice( LocalDateTime.MAX, LocalDateTime.now(), 2.0, 0, 1 ) );
    }

}
