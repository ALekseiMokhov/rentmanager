package ru.rambler.alexeimohov.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.SubscriptionDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.service.events.OrderCreatedEvent;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;

import java.util.List;

@Service
public interface IUserService extends UserDetailsService {

    UserDto getById(Long id);

    void remove(Long id);

    List <UserDto> getAll();

    UserDto getByUserName(String userName);

    void setSubscription(SubscriptionDto dto);

    void removeSubscription(long id);

    void addCreditCard(long id, CardDto cardDto);

    void removeCreditCard(long id, CardDto cardDto);

    void addMessage(long id, MessageDto messageDto);

    void removeMessage(long id, MessageDto messageDto);

    void saveOrUpdate(UserDto dto);

    @TransactionalEventListener
    void onOrderCreatedEvent(OrderCreatedEvent event);

    @TransactionalEventListener
    void onOrderFinishedEvent(OrderFinishedEvent event);

}
