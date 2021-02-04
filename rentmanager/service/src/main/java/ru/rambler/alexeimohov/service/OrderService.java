package ru.rambler.alexeimohov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.OrderDao;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.OrderMapper;
import ru.rambler.alexeimohov.entities.Order;
import ru.rambler.alexeimohov.entities.enums.OrderStatus;
import ru.rambler.alexeimohov.service.events.OrderCreatedEvent;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;
import ru.rambler.alexeimohov.service.interfaces.IOrderService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OrderService implements IOrderService {
    @Value("#{T(Double).parseDouble('${blocking.coefficient}')}")
    private Double blockingFundsCoef;

    private OrderDao orderDao;

    private OrderMapper orderMapper;

    private ApplicationEventPublisher publisher;


    public OrderService(OrderDao orderDao, OrderMapper orderMapper, ApplicationEventPublisher publisher) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
        this.publisher = publisher;
    }

    @Override
    public OrderDto getById(Long id) {
        return orderMapper.toDto(orderDao.findById(id));
    }

    @Transactional(readOnly = false)
    @Override
    public void saveOrUpdate(OrderDto dto) {

        Order order = orderMapper.fromDto(dto);
        log.debug(order.toString());

        if (order.getCreationTime() == null) {
            order.setCreationTime(LocalDateTime.now());
        }
        /*
        calculates amount to be blocked in users card*/
        if (order.getBlockedFunds() == 0) {
            order.setBlockedFunds(blockingFundsCoef * order.getVehicle().getRentPrice());
        }
       /*For new Order checks whether Vehicle is free for the Date and amount of funds on user's card is sufficient
       calling @method
       * @throws IllegalArgumentException*/
        if (order.getId() == null && isValidRequirements(order) == false) {
            throw new IllegalArgumentException("Check card's balance and vehicle's booked dates!");
        }
        /*Set status 'created'*/
        if (order.getId() == null) {
            if (order.getStatus() == null) {
                order.setStatus(OrderStatus.CREATED);
            }
            log.debug(order.toString());
            orderDao.save(order);
            publisher.publishEvent(new OrderCreatedEvent(orderMapper.toDto(order)));
            log.debug("order created : " + order.getId());
        } else {
            orderDao.update(order);
            log.debug("order updated : " + order.getId());
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(Long id) {
        orderDao.remove(id);
        log.debug("order deleted : " + id);
    }


    @Transactional(readOnly = false)
    @Override
    public void finish(Long id) {
        Order order = orderDao.findById(id);

        order.setFinishTime(LocalDateTime.now());
        if (order.isHasValidSubscription() == false) {
            order.setTotalPrice(
                    countTotalPrice(order.getStartTime(), order.getFinishTime(),
                            order.getVehicle().getRentPrice(), order.getVehicle().getFinePrice(), order.getUser().getPrivilege().getCoefficient()));
        }
        order.setStatus(OrderStatus.FINISHED);
        publisher.publishEvent(new OrderFinishedEvent(orderMapper.toDto(order)));
        log.debug("event was published ");

    }

    @Transactional(readOnly = false)
    @Override
    public void cancel(Long id) {
        Order order = orderDao.findById(id);
        order.setTotalPrice(0.0);
        order.setStatus(OrderStatus.CANCELLED);
        publisher.publishEvent(new OrderFinishedEvent(orderMapper.toDto(order)));
        log.debug("event was published ");
    }

    @Override
    public List<OrderDto> getAll() {
        return orderMapper.listToDto(orderDao.findAll());
    }


    /*
    @method counts total price
    * */
    public double countTotalPrice(LocalDateTime start, LocalDateTime end, double price, double fine, double coefficient) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Finish time can't be before start time!");
        }
        long hours = LocalDateTime.from(start).until(end, ChronoUnit.HOURS);
        return (price * hours + fine) * coefficient;
    }

    /*@methods validates requirements*/
    public boolean isValidRequirements(Order order) {
        if (
                orderDao.getAvailableFunds(order.getCreditCardNumber()) <= order.getBlockedFunds()) {
            return false;
        }
        if (
                orderDao.getBookedDatesOfChosenVehicle(order.getVehicle().getId())
                        .contains(java.sql.Date.valueOf(order.getStartTime().toLocalDate()))) {

            return false;
        }
        return true;
    }

}
