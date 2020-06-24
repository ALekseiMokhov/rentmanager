package com.senla.carservice.controller;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.domain.entities.order.OrderStatus;
import com.senla.carservice.domain.service.IOrderService;
import com.senla.carservice.domain.service.OrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class OrderController {
    private IOrderService orderService;

    public OrderController() {

        this.orderService = OrderService.getInstance();
    }


    public void addOrder(LocalDate date, LocalDate startOfExecution, Set <Speciality> required) {
        this.orderService.addOrder( date, startOfExecution, required );
    }

    public void addOrder(LocalDate date, LocalDate startOfExecution, List <IMaster> masters, Place place, UUID id) {
        this.orderService.addOrder( date, startOfExecution, masters, place, id );
    }

    public void loadOrder(Order order) {
        this.orderService.saveOrder( order );
    }

    public Order findOrderById(UUID id) {
        return this.orderService.findOrderById( id );
    }

    public void shiftOrderExecutionDate(UUID id, LocalDate newDate) {
        this.orderService.shiftOrderExecutionDate( id, newDate );
    }

    public void setNewMasters(UUID id) {
        this.orderService.setNewMasters( id );
    }

    public void cancelOrder(UUID id) {
        this.orderService.cancelOrder( id );
    }

    public void completeOrder(UUID id) {
        this.orderService.completeOrder( id );
    }

    public List <Order> getOrders() {
        return this.orderService.getOrders();
    }

    public List <Order> getOrdersByBookedDate(OrderStatus status) {
        return this.orderService.getOrdersByBookedDate( status );
    }

    public List <Order> getOrdersByExecutionDate(OrderStatus status) {
        return this.orderService.getOrdersByExecutionDate( status );
    }

    public List <Order> getOrdersForPeriod(LocalDate start, LocalDate end) {
        return this.orderService.getOrdersForPeriod( start, end );
    }
}
