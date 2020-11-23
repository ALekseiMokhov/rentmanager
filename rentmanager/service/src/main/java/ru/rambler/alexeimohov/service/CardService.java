package ru.rambler.alexeimohov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.CardDao;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.CardMapper;
import ru.rambler.alexeimohov.entities.Card;
import ru.rambler.alexeimohov.service.interfaces.ICardService;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class CardService implements ICardService {

    private CardDao cardDao;

    private CardMapper cardMapper;

    public CardService(CardDao cardDao, CardMapper cardMapper) {
        this.cardDao = cardDao;
        this.cardMapper = cardMapper;
    }
    @Transactional(readOnly = false)
    @Override
    public void saveOrUpdate(CardDto dto) {
        Card card = cardMapper.fromDto( dto );
        if (cardDao.findById( card.getId() ) == null) {
            cardDao.save( card );
            log.debug( "card been saved: "+ card.getCreditCardNumber());
        } else {
            cardDao.update( card );
            log.debug( "card been updated: "+ card.getCreditCardNumber());

        }

    }
    @Transactional(readOnly = false)
    @Override
    public void remove(Long id) {
        cardDao.remove( id );
        log.info( "card deleted: " +id );
    }

    @Override
    public CardDto getByCardNumber(Long cardNumber) {
        return cardMapper.toDto( cardDao.findByCardNumber( cardNumber ) );

    }

    @Override
    public CardDto getById(Long id) {
        return cardMapper.toDto( cardDao.findById( id ) );
    }

    @Override
    public List <CardDto> getAll() {
        return cardMapper.listToDto( cardDao.findAll() );
    }
}
