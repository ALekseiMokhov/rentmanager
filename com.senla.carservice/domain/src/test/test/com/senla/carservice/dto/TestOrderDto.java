package com.senla.carservice.dto;

import com.senla.carservice.dto.mappers.OrderMapper;
import com.senla.carservice.entity.order.Order;
import com.senla.carservice.entity.order.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class TestOrderDto {
    LocalDate start;
    LocalDate finish;

    @BeforeEach
    void init() {
        start = LocalDate.of(2100, 1, 1);
        finish = LocalDate.of(2100, 1, 2);
    }

    @Test
    void shouldMapOrderToDto() {
        /*given*/
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setDateBooked(LocalDate.now());
        order.setStartOfExecution(start);
        order.setFinishOfExecution(finish);
        order.setStatus(OrderStatus.COMPLETED);

        /*when*/
        OrderDto dto = OrderMapper.INSTANCE.dtoFromOrder(order);

        /*then*/
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(order.getId(), UUID.fromString(dto.getId()));
        Assertions.assertEquals(order.getStatus(), OrderStatus.valueOf(dto.getStatus()));
        Assertions.assertEquals(order.getFinishOfExecution().toString(), dto.getFinishOfExecution());

    }

    @Test
    void shouldMapDtoToOrder() {
        /*given*/
        OrderDto dto = new OrderDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setDateBooked(String.valueOf(LocalDate.now()));
        dto.setStartOfExecution(String.valueOf(start));
        dto.setFinishOfExecution(String.valueOf(finish));
        dto.setStatus(String.valueOf(OrderStatus.COMPLETED));

        /*when*/
        Order order = OrderMapper.INSTANCE.orderFromDto(dto);

        /*then*/
        Assertions.assertNotNull(order);
        Assertions.assertEquals(dto.getId(), String.valueOf(order.getId()));
        Assertions.assertEquals(dto.getDateBooked(), String.valueOf(order.getDateBooked()));
        Assertions.assertEquals(dto.getStatus(), String.valueOf(order.getStatus()));


    }
}
