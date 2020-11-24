package ru.rambler.alexeimohov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rambler.alexeimohov.dao.interfaces.OrderDao;
import ru.rambler.alexeimohov.dao.jpa.OrderJpaDaoImpl;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.VehicleDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.*;
import ru.rambler.alexeimohov.entities.Order;
import ru.rambler.alexeimohov.entities.User;
import ru.rambler.alexeimohov.entities.Vehicle;
import ru.rambler.alexeimohov.entities.enums.OrderStatus;
import ru.rambler.alexeimohov.service.interfaces.IUserService;
import ru.rambler.alexeimohov.service.interfaces.IVehicleService;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TestOrderService {
    private OrderDao orderDao = Mockito.mock( OrderJpaDaoImpl.class );

    private IUserService userService = Mockito.mock( UserService.class );

    private IVehicleService vehicleService = Mockito.mock( VehicleService.class );

    private OrderMapper orderMapper = Mockito.mock( OrderMapperImpl.class );

    private UserMapper userMapper = Mockito.mock( UserMapperImpl.class );

    private VehicleMapper vehicleMapper = Mockito.mock( VehicleMapperImpl.class );

    @InjectMocks
    private OrderService orderService;

    private OrderDto orderDto;

    private Vehicle vehicle;

    private Order order;

    private User user;


    @BeforeEach
    void init() {
        this.user = new User();
        user.setFullName( "Alex" );

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
        orderService.saveOrUpdate( orderDto );
        //then
        /*verify( userService,times( 1 ) ) .getByUserName( anyString() ) ;*/
         /*verify( vehicleService,times( 1 ) ) .getById( anyLong() ) ;
         verify( vehicleService,times( 1 ) ) .setDateForBooking( any(),any() ); ;*/

    }
}
