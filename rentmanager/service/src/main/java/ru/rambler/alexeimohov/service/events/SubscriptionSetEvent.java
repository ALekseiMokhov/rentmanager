package ru.rambler.alexeimohov.service.events;

import lombok.Getter;
import lombok.Setter;
import ru.rambler.alexeimohov.dto.SubscriptionDto;

@Getter
@Setter
public class SubscriptionSetEvent {

    private SubscriptionDto subscriptionDto;

    public SubscriptionSetEvent(SubscriptionDto subscriptionDto) {
        this.subscriptionDto = subscriptionDto;
    }
}
