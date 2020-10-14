package com.senla.carservice.dto.mappers.interfaces;

import com.senla.carservice.dto.OrderDto;
import com.senla.carservice.dto.mappers.OrderStatusMapper;
import com.senla.carservice.dto.mappers.UuidMapper;
import com.senla.carservice.entity.order.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {UuidMapper.class, OrderStatusMapper.class}, componentModel = "spring")
public interface OrderMapper {
/*
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
*/

    OrderDto dtoFromOrder(Order order);

    Order orderFromDto(OrderDto orderDto);

    List<OrderDto> dtoFromOrders(List<Order> orders);

    List<Order> ordersFromDto(List<OrderDto> dto);

}
