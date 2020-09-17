package com.senla.carservice.controller;

import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.entity.master.AbstractMaster;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.entity.order.Order;
import com.senla.carservice.entity.order.OrderStatus;
import com.senla.carservice.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
public class OrderController implements IController {
    @Autowired
    @Qualifier("orderService")
    private IOrderService orderService;

    public OrderController() {


    }


    public void addOrder(LocalDate date, LocalDate startOfExecution, Set <Speciality> required) {
        this.orderService.addOrder( date, startOfExecution, required );
    }

    public void addOrder(LocalDate date, LocalDate startOfExecution, List <AbstractMaster> masters, Place place, UUID id) {
        this.orderService.addOrder( date, startOfExecution, masters, place, id );
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

    public void deleteOrder(UUID id) {
        this.orderService.deleteOrder( id );
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
    public List <Order> getOrdersByPrice(OrderStatus status){
        return this.orderService.getOrdersByPrice(status);
    }

    public void loadFromCsv() {
        this.orderService.loadFromCsv();
    }

    public void exportToCsv() {
        this.orderService.exportToCsv();
    }

    public void loadFromJson() {
        this.orderService.loadOrdersFromJson();
    }

    public void exportToJson() {
        this.orderService.exportOrdersToJson();
    }
}
