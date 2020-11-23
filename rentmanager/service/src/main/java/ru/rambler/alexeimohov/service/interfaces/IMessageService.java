package ru.rambler.alexeimohov.service.interfaces;

import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;

import java.util.List;

public interface IMessageService {

    void saveOrUpdate(MessageDto dto);

    void sendMessage(OrderFinishedEvent event) ;

    MessageDto getById(Long id);

    List <MessageDto> getAll();

    void remove(Long id);

}
