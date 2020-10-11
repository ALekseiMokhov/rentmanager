package com.senla.carservice.controller;

import com.senla.carservice.dto.OrderDto;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.entity.order.OrderStatus;
import com.senla.carservice.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
/*@Profile({"rest", "test"})*/

public class OrderRestController {
    @Autowired
    @Qualifier("orderService")
    private IOrderService orderService;

    @PostMapping("/{date}/{start}/{required}")
    public void addOrder(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startOfExecution,
                         @PathVariable Set<Speciality> required) {
        this.orderService.addOrder(date, startOfExecution, required);
    }

    @GetMapping("/{id}")
    public OrderDto findOrderById(@PathVariable UUID id) {

        return this.orderService.findOrderById(id);
    }

    @PatchMapping("/{id}/{date}")
    public void shiftOrderExecutionDate(@PathVariable UUID id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate newDate) {
        this.orderService.shiftOrderExecutionDate(id, newDate);
    }

    @PatchMapping("/new-masters/{id}")
    public void setNewMasters(@PathVariable UUID id) {
        this.orderService.setNewMasters(id);
    }

    @PatchMapping("/cancel/{id}")
    public void cancelOrder(@PathVariable UUID id) {
        this.orderService.cancelOrder(id);
    }

    @PatchMapping("/complete/{id}")
    public void completeOrder(@PathVariable UUID id) {
        this.orderService.completeOrder(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable UUID id) {
        this.orderService.deleteOrder(id);
    }

    @GetMapping("/")
    public List<OrderDto> getOrders() {
        return this.orderService.getOrders();
    }

    @GetMapping("/booked-date/{status}")
    public List<OrderDto> getOrdersByBookedDate(@PathVariable OrderStatus status) {
        return this.orderService.getOrdersByBookedDate(status);
    }

    @GetMapping("/execution/{status}")
    public List<OrderDto> getOrdersByExecutionDate(@PathVariable OrderStatus status) {
        return this.orderService.getOrdersByExecutionDate(status);
    }

    @GetMapping("/{start}/{end}")
    public List<OrderDto> getOrdersForPeriod(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                             @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        return this.orderService.getOrdersForPeriod(start, end);
    }

    @GetMapping("/by-price/{status}")
    public List<OrderDto> getOrdersByPrice(@PathVariable OrderStatus status) {

        return this.orderService.getOrdersByPrice(status);
    }


}
