package ru.rambler.alexeimohov.service.interfaces;

import org.springframework.stereotype.Service;
import ru.rambler.alexeimohov.dto.CardDto;

import java.util.List;

@Service
public interface ICardService {

    CardDto getByCardNumber(long cardNumber);

    CardDto getById(long id);

    List<CardDto> getByUserName(String userName);

    List<CardDto> getAll();


}
