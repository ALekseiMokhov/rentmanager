package ru.rambler.alexeimohov.service.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.service.events.OrderCreatedEvent;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;

import java.util.List;

@Service
public interface ICardService {

    CardDto getByCardNumber(long cardNumber);

    CardDto getById(long id);

    List <CardDto> getByUserName(String userName);

    List <CardDto> getAll();


}
