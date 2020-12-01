package ru.rambler.alexeimohov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.rambler.alexeimohov.dao.interfaces.CardDao;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.CardMapper;
import ru.rambler.alexeimohov.entities.Card;
import ru.rambler.alexeimohov.service.events.OrderCreatedEvent;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;
import ru.rambler.alexeimohov.service.interfaces.ICardService;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CardService implements ICardService {

    private CardDao cardDao;

    private CardMapper cardMapper;

    public CardService(CardDao cardDao, CardMapper cardMapper) {
        this.cardDao = cardDao;
        this.cardMapper = cardMapper;
    }


    @Override
    public CardDto getByCardNumber(long cardNumber) {
        return cardMapper.toDto( cardDao.findByCardNumber( cardNumber ) );

    }

    @Override
    public CardDto getById(long id) {
        return cardMapper.toDto( cardDao.findById( id ) );
    }

    @Override
    public List <CardDto> getByUserName(String userName) {
        return cardMapper.listToDto( cardDao.findAllByUserName(userName) );

    } @Override
    public List <CardDto> getAll() {
        return cardMapper.listToDto( cardDao.findAll() );
    }



}
