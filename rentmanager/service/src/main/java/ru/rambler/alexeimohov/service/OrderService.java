package ru.rambler.alexeimohov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.OrderDao;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.MessageMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.OrderMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.VehicleMapper;
import ru.rambler.alexeimohov.entities.Order;
import ru.rambler.alexeimohov.entities.User;
import ru.rambler.alexeimohov.entities.Vehicle;
import ru.rambler.alexeimohov.entities.enums.OrderStatus;
import ru.rambler.alexeimohov.service.interfaces.IMessageService;
import ru.rambler.alexeimohov.service.interfaces.IOrderService;
import ru.rambler.alexeimohov.service.interfaces.IUserService;
import ru.rambler.alexeimohov.service.interfaces.IVehicleService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class OrderService implements IOrderService {

    private OrderDao orderDao;

    private IUserService userService;

    private IVehicleService vehicleService;

    private IMessageService messageService;

    private UserMapper userMapper;

    private VehicleMapper vehicleMapper;

    private MessageMapper messageMapper;

    private OrderMapper orderMapper;


    public OrderService(OrderDao orderDao, IUserService userService, IVehicleService vehicleService,
                        IMessageService messageService, UserMapper userMapper,
                        VehicleMapper vehicleMapper, MessageMapper messageMapper, OrderMapper orderMapper) {
        this.orderDao = orderDao;
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.messageService = messageService;
        this.userMapper = userMapper;
        this.vehicleMapper = vehicleMapper;
        this.messageMapper = messageMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDto getById(Long id) {
        return orderMapper.toDto( orderDao.findById( id ) );
    }

    @Override
    public void saveOrUpdate(OrderDto dto) {
        Order order = orderMapper.fromDto( dto );

        User user = userMapper.fromDto(userService.getByUserName( dto.getUserName() )  );
        if(user.getSubscription()!=null && user.getSubscription().getExpirationDate().isBefore( order.getStartTime().toLocalDate() ))
                    {
                        order.setHasValidSubscription( true );
                    }
        order.setUser( user );

        Vehicle vehicle = vehicleMapper.fromDto( vehicleService.getById( Long.valueOf( dto.getVehicleId() ) ) );
        vehicleService.setDateForBooking( vehicle.getId(),order.getStartTime().toLocalDate() );
        order.setVehicle( vehicle );

        if (orderDao.findById( order.getId() ) == null) {
            orderDao.save( order );
        } else {
            orderDao.update( order );
        }
    }

    @Override
    public void remove(Long id) {
        orderDao.remove( id );
    }

    @Override
    public void finish(Long id) {
      Order order = orderDao.findById( id );
      order.setFinishTime( LocalDateTime.now() );
      if(order.isHasValidSubscription()==false){
                order.setTotalPrice(
                        countTotalPrice( order.getStartTime(),order.getFinishTime(),
                                order.getVehicle().getRentPrice() ,order.getVehicle().getFinePrice() )  );
      }
        orderDao.findById( id ).setStatus( OrderStatus.FINISHED );
      messageService.sendMessage( null/* text from property*/ );

    }

    @Override
    public void cancel(Long id) {
      orderDao.findById( id ).setStatus( OrderStatus.CANCELLED );
    }

    @Override
    public List <OrderDto> getAll() {
        return orderMapper.listToDto( orderDao.findAll() );
    }

    private double countTotalPrice(LocalDateTime start, LocalDateTime end, double price, double fine)   {
       long hours = LocalDateTime.from( start ).until( end, ChronoUnit.HOURS     ) ;
       return  (price*hours + fine);
    }
}
