package ru.rambler.alexeimohov.service.events;

import lombok.Getter;
import lombok.Setter;

   @Getter
   @Setter
public class OrderFinishedEvent {

    private Long userId;

    private Long vehicleId;

    public OrderFinishedEvent( Long userId, Long vehicleId) {

        this.userId = userId;
        this.vehicleId = vehicleId;    }
}
