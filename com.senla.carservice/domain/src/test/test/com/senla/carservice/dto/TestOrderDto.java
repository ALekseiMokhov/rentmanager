package com.senla.carservice.dto;

import com.senla.carservice.dto.mappers.OrderMapper;
import com.senla.carservice.entity.order.Order;
import com.senla.carservice.entity.order.OrderStatus;
import com.senla.carservice.spring.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestOrderDto {
    @Autowired
    private OrderMapper mapper;

    private LocalDate start;
    private LocalDate finish;

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
        OrderDto dto = mapper.dtoFromOrder(order);

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
        Order order = mapper.orderFromDto(dto);

        /*then*/
        Assertions.assertNotNull(order);
        Assertions.assertEquals(dto.getId(), String.valueOf(order.getId()));
        Assertions.assertEquals(dto.getDateBooked(), String.valueOf(order.getDateBooked()));
        Assertions.assertEquals(dto.getStatus(), String.valueOf(order.getStatus()));


    }
}
