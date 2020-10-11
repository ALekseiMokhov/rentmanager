package com.senla.carservice.service.interfaces;


import com.senla.carservice.dto.MasterDto;
import com.senla.carservice.dto.OrderDto;
import com.senla.carservice.dto.PlaceDto;
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

    void saveOrder(OrderDto orderDto, List<MasterDto> masterDtos, PlaceDto placeDto);

    OrderDto findOrderById(UUID id);

    void shiftOrderExecutionDate(UUID id, LocalDate newDate);

    void setNewMasters(UUID id);

    void cancelOrder(UUID id);

    void completeOrder(UUID id);

    void deleteOrder(UUID id);

    List<OrderDto> getOrders();

    List<OrderDto> getOrdersByBookedDate(OrderStatus status);

    List<OrderDto> getOrdersByExecutionDate(OrderStatus status);

    List<OrderDto> getOrdersForPeriod(LocalDate start, LocalDate end);

    List<OrderDto> getOrdersByPrice(OrderStatus status);


}
