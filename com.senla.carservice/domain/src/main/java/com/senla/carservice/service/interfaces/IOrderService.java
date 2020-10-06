package com.senla.carservice.service.interfaces;


import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.entity.order.Order;
import com.senla.carservice.entity.order.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public interface IOrderService {
    void addOrder(LocalDate date, LocalDate startOfExecution, Set<Speciality> required);

    void saveOrder(Order order);

    Order findOrderById(UUID id);

    void shiftOrderExecutionDate(UUID id, LocalDate newDate);

    void setNewMasters(UUID id);

    void cancelOrder(UUID id);

    void completeOrder(UUID id);

    void deleteOrder(UUID id);

    List<Order> getOrders();

    List<Order> getOrdersByBookedDate(OrderStatus status);

    List<Order> getOrdersByExecutionDate(OrderStatus status);

    List<Order> getOrdersForPeriod(LocalDate start, LocalDate end);

    List<Order> getOrdersByPrice(OrderStatus status);


}
