package ru.rambler.alexeimohov.service.interfaces;

import ru.rambler.alexeimohov.dto.CardDto;

import java.util.List;

public interface ICardService {

    void saveOrUpdate(CardDto dto);

    void remove(Long id);

    CardDto getByCardNumber(Long cardNumber);

    CardDto getById(Long id);

    List <CardDto> getAll();
}
