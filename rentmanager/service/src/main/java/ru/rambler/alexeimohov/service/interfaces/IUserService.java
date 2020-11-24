package ru.rambler.alexeimohov.service.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;

import java.util.List;

@Service
public interface IUserService {

    UserDto getById(Long id);

    void remove(Long id);

    List <UserDto> getAll();

    UserDto getByUserName(String userName);

    void addCreditCard(long id, CardDto cardDto);

    void removeCreditCard(long id, CardDto cardDto);

    void addMessage(long id, MessageDto messageDto);

    void removeMessage(long id, MessageDto messageDto);

    @TransactionalEventListener
    void onApplicationEvent(OrderFinishedEvent event);

    void saveOrUpdate(UserDto dto);
}
