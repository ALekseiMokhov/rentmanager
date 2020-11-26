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
        if (card.getId() == null) {
            cardDao.save( card );
            log.debug( "card been saved: " + card.getCreditCardNumber() );
        } else {
            cardDao.update( card );
            log.debug( "card been updated: " + card.getCreditCardNumber() );

        }

    }

    @Transactional(readOnly = false)
    @Override
    public void remove(Long id) {
        cardDao.remove( id );
        log.info( "card deleted: " + id );
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
    public List <CardDto> getAll() {
        return cardMapper.listToDto( cardDao.findAll() );
    }


    @TransactionalEventListener
    public void onOrderFinishedEvent(OrderFinishedEvent event) {
        log.info( "triggered by order finished event" );
        Card card = cardDao.findByCardNumber( Long.valueOf( event.getOrderDto().getCreditCardNumber() ) );
        log.info( "Card retrieved: " + card.getId() + "\n" + "card's available funds: " + card.getAvailableFunds() );

        card.unBlockFunds( Double.parseDouble( event.getOrderDto().getBlockedFunds() ) );
        card.writeOff( Double.parseDouble( event.getOrderDto().getTotalPrice() ) );
        cardDao.update( card );

        log.info( "Card retrieved: " + card.getId() + "\n" + "card's available funds: " + card.getAvailableFunds() );

    }

    @TransactionalEventListener
    public void onOrderCreatedEvent(OrderCreatedEvent event) {
        log.info( "triggered by order created event" );
        Card card = cardDao.findByCardNumber( Long.valueOf( event.getOrderDto().getCreditCardNumber() ) );
        log.info( "Card retrieved: " + card.getId() + "\n" + "card's available funds: " + card.getAvailableFunds() );

        card.blockFunds( Double.parseDouble( event.getOrderDto().getBlockedFunds() ) );
        card.writeOff( Double.parseDouble( event.getOrderDto().getBlockedFunds() ) );
        cardDao.update( card );

        log.info( "Card retrieved: " + card.getId() + "\n" + "card's available funds: " + card.getAvailableFunds() );

    }
}
