package com.senla.carservice.dto.mappers;

import com.senla.carservice.dto.OrderDto;
import com.senla.carservice.entity.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UuidMapper.class, OrderStatusMapper.class})
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto dtoFromOrder(Order order);

    Order orderFromDto(OrderDto orderDto);

}
