package ru.rambler.alexeimohov.service.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OrderCancelledEvent  {

    private Long userId;

    private Long vehicleId;

    public OrderCancelledEvent(Long userId, Long vehicleId) {
        this.userId = userId;
        this.vehicleId = vehicleId;
    }
}
