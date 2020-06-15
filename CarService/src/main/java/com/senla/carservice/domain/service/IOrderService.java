package com.senla.carservice.domain.service;

import com.senla.carservice.domain.entities.master.Speciality;
import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.domain.entities.order.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IOrderService {
    void addOrder(LocalDate date, LocalDate startOfExecution, Set <Speciality> required);
    Order findOrderById(UUID id) ;
    void shiftOrderExecutionDate(Order order, LocalDate newDate);
    void setNewMasters(Order order);
    void cancelOrder(UUID id);
    void completeOrder(UUID id);
    List <Order> getOrders();
    List <Order> getOrdersByBookedDate(OrderStatus status);
    List <Order> getOrdersByExecutionDate(OrderStatus status);
    List <Order> getOrdersForPeriod(LocalDate start, LocalDate end);
}
