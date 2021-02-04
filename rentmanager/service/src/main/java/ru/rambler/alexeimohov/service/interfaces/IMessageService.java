package ru.rambler.alexeimohov.service.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.service.events.OrderCreatedEvent;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;
import ru.rambler.alexeimohov.service.events.SubscriptionSetEvent;
import ru.rambler.alexeimohov.service.events.UserRegisteredEvent;

import java.util.List;

@Service
public interface IMessageService {

    MessageDto getById(Long id);

    List<MessageDto> getAll();

    void sendCustomMessage(UserDto to, String topic, String text);

    @TransactionalEventListener
    void sendMessageAfterCompleteOrder(OrderFinishedEvent event);

    @TransactionalEventListener
    void sendMessageAfterCreateOrder(OrderCreatedEvent event);

    @TransactionalEventListener
    void sendMessageAfterSetSubscription(SubscriptionSetEvent event);

    @TransactionalEventListener
    void greetNewUser(UserRegisteredEvent event);


}
