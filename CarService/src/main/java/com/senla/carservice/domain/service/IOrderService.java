package com.senla.carservice.domain.service;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.domain.entities.order.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IOrderService {
    void addOrder(LocalDate date, LocalDate startOfExecution, Set <Speciality> required);

    void addOrder(LocalDate date, LocalDate startOfExecution, List <IMaster> masters, Place place, UUID id);

    void saveOrder(Order order);

    Order findOrderById(UUID id);

    void shiftOrderExecutionDate(UUID id, LocalDate newDate);

    void setNewMasters(UUID id);

    void cancelOrder(UUID id);

    void completeOrder(UUID id);

    List <Order> getOrders();

    List <Order> getOrdersByBookedDate(OrderStatus status);

    List <Order> getOrdersByExecutionDate(OrderStatus status);

    List <Order> getOrdersForPeriod(LocalDate start, LocalDate end);
}
