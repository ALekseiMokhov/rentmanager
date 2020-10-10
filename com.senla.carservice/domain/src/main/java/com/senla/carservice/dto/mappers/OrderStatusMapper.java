package com.senla.carservice.dto.mappers;

import com.senla.carservice.entity.order.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusMapper {
    public OrderStatus asStatus(String dtoStatus) {
        OrderStatus status = OrderStatus.valueOf(dtoStatus);
        return status;
    }

    public String asString(OrderStatus enumStatus) {
        return String.valueOf(enumStatus);
    }
}
