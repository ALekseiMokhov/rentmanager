package ru.rambler.alexeimohov.service.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;

import java.util.List;

@Service
public interface IMessageService {

    void saveOrUpdate(MessageDto dto);

    MessageDto getById(Long id);

    List <MessageDto> getAll();

    void remove(Long id);

    @TransactionalEventListener
    void sendMessage(OrderFinishedEvent event) ;


}
