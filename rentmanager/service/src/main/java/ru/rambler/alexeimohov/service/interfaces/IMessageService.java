package ru.rambler.alexeimohov.service.interfaces;

import ru.rambler.alexeimohov.dto.MessageDto;

import java.util.List;

public interface IMessageService {

    void saveOrUpdate(MessageDto dto);

    MessageDto getById(Long id);

    List <MessageDto> getAll();

    void remove(Long id);

}
