package ru.rambler.alexeimohov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
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
import ru.rambler.alexeimohov.service.events.OrderCancelledEvent;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;
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
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OrderService implements IOrderService  {

    private OrderDao orderDao;

    private OrderMapper orderMapper;

    private ApplicationEventPublisher publisher;


    public OrderService(OrderDao orderDao, OrderMapper orderMapper) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDto getById(Long id) {
        return orderMapper.toDto( orderDao.findById( id ) );
    }
    
    @Transactional(readOnly = false)
    @Override
    public void saveOrUpdate(OrderDto dto) {
        Order order = orderMapper.fromDto( dto );
         if((order.getId()==null) ||(orderDao.findById( order.getId() ) == null) ){
            orderDao.save( order );
            log.debug( "order created : "+order.getId() );
        } else {
            orderDao.update( order );
             log.debug( "order updated : "+order.getId() );
        }
    }
    @Transactional(readOnly = false)
    @Override
    public void remove(Long id) {
        orderDao.remove( id );
        log.debug( "order deleted : "+ id );
    }


    @Transactional(readOnly = false)
    @Override
    public void finish(Long id) {
      Order order = orderDao.findById( id );
      order.setFinishTime( LocalDateTime.now() );
      if(order.isHasValidSubscription()==false){
                order.setTotalPrice(
                        countTotalPrice( order.getStartTime(),order.getFinishTime(),
                                order.getVehicle().getRentPrice() ,order.getVehicle().getFinePrice() )  );
      }
        order.setStatus( OrderStatus.FINISHED );
        publisher.publishEvent( new OrderFinishedEvent( order.getUser().getId(),order.getVehicle().getId() ) );
        log.debug( "event was published " );

    }
    @Transactional(readOnly = false)
    @Override
    public void cancel(Long id) {
        Order order =  orderDao.findById( id );
        order.setStatus( OrderStatus.CANCELLED );
        publisher.publishEvent( new OrderCancelledEvent( order.getUser().getId(),order.getVehicle().getId() ) );
        log.debug( "event was published " );
    }

    @Override
    public List <OrderDto> getAll() {
        return orderMapper.listToDto( orderDao.findAll() );
    }

    
        // count total price
    private double countTotalPrice(LocalDateTime start, LocalDateTime end, double price, double fine)   {
        long hours = LocalDateTime.from( start ).until( end, ChronoUnit.HOURS     ) ;
        return  (price*hours + fine);
    }

}
