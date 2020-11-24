package ru.rambler.alexeimohov.service.events;

import lombok.Getter;
import lombok.Setter;
import ru.rambler.alexeimohov.dto.OrderDto;
@Getter
@Setter
public class OrderCreatedEvent {
    private OrderDto orderDto;

    public OrderCreatedEvent(OrderDto orderDto) {
        this.orderDto = orderDto;
    }
}
