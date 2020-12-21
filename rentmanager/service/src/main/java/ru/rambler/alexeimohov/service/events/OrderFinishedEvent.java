package ru.rambler.alexeimohov.service.events;

import lombok.Getter;
import lombok.Setter;
import ru.rambler.alexeimohov.dto.OrderDto;

@Getter
@Setter
public class OrderFinishedEvent {
    private OrderDto orderDto;

    public OrderFinishedEvent(OrderDto orderDto) {
        this.orderDto = orderDto;
    }
}
