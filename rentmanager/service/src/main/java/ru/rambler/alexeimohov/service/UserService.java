package ru.rambler.alexeimohov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.rambler.alexeimohov.dao.interfaces.UserDao;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.SubscriptionDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.CardMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.MessageMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.SubscriptionMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.entities.Card;
import ru.rambler.alexeimohov.entities.Subscription;
import ru.rambler.alexeimohov.entities.User;
import ru.rambler.alexeimohov.service.events.*;
import ru.rambler.alexeimohov.service.interfaces.IUserService;

import java.util.List;

/*
 * Should be used as service to add/remove messages and credit cards as parent entity service
 * Parent side association of Subscription*/
@Service
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService implements IUserService {

    private UserDao userDao;

    private UserMapper userMapper;

    private CardMapper cardMapper;

    private SubscriptionMapper subscriptionMapper;

    private ApplicationEventPublisher publisher;

    private MessageMapper messageMapper;

    public UserService(UserDao userDao, UserMapper userMapper, CardMapper cardMapper,
                       SubscriptionMapper subscriptionMapper, ApplicationEventPublisher publisher, MessageMapper messageMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.cardMapper = cardMapper;
        this.subscriptionMapper = subscriptionMapper;
        this.publisher = publisher;
        this.messageMapper = messageMapper;
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toDto( userDao.findById( id ) );
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(Long id) {
        userDao.remove( id );
    }

    @Override
    public List <UserDto> getAll() {
        return userMapper.listToDto( userDao.findAll() );
    }

    @Override
    public UserDto getByUserName(String userName) {
        return userMapper.toDto( userDao.findByUserName( userName ) );
    }

    @Transactional(readOnly = false)
    @Override
    public void saveOrUpdate(UserDto dto) {
        User user = userMapper.fromDto( dto );
        if (user.getId() == null) {
            userDao.save( user );
            log.debug( "user has been saved: " + user.getUsername() );
            publisher.publishEvent( new UserRegisteredEvent( userMapper.toDto( user ) ) );
        } else {
            userDao.update( user );
            log.debug( "user has been updated: " + user.getUsername() );
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void setSubscription(SubscriptionDto dto) {
        Subscription subscription = subscriptionMapper.fromDto( dto );
        User user = userDao.findByUserName( subscription.getUser().getUsername() );
        user.setSubscription( subscription );
        publisher.publishEvent( new SubscriptionSetEvent( subscriptionMapper.toDto( subscription ) ) );
    }

    @Override
    @Transactional(readOnly = false)

    public void removeSubscription(long id) {
        User user = userDao.findById( id );
        user.setSubscription( null );
    }

    @Transactional(readOnly = false)
    @Override
    public void addCreditCard(long id, CardDto carDto) {
        User user = userDao.findById( id );
        user.addCreditCard( cardMapper.fromDto( carDto ) );
        log.debug( "user's cards " + user.getCreditCards().size() );
    }

    @Transactional(readOnly = false)
    @Override
    public void removeCreditCard(long id, CardDto carDto) {
        User user = userDao.findById( id );
        user.removeCreditCard( cardMapper.fromDto( carDto ) );
    }

    @Transactional(readOnly = false)
    @Override
    public void addMessage(long id, MessageDto messageDto) {
        User user = userDao.findById( id );
        user.addMessage( messageMapper.fromDto( messageDto ) );

    }

    @Transactional(readOnly = false)
    @Override
    public void removeMessage(long id, MessageDto messageDto) {
        User user = userDao.findById( id );
        user.removeMessage( messageMapper.fromDto( messageDto ) );
    }

    @TransactionalEventListener
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void onOrderFinishedEvent(OrderFinishedEvent event) {
        User retrieved = userDao.findByUserName( event.getOrderDto().getUserDto().getFullName() );
        log.debug( "_____USER_____: " + retrieved.toString() );
        Card card = retrieved.getCreditCards().stream()
                .filter( c -> c.getCreditCardNumber() == Long.parseLong( event.getOrderDto().getCreditCardNumber() ) )
                .findFirst()
                .get();
        log.debug( "_____USER_____: " + card.toString() );
        log.debug( "Card retrieved: " + card.getId() + "\n" + "card's available funds: " + card.getAvailableFunds() );

        card.unBlockFunds( Double.parseDouble( event.getOrderDto().getBlockedFunds() ) );
        card.writeOff( Double.parseDouble( event.getOrderDto().getTotalPrice() ) );

        userDao.update( retrieved );

        log.debug( "Card retrieved: " + card.getId() + "\n" + "card's available funds: " + card.getAvailableFunds() );

    }


    @TransactionalEventListener
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void onOrderCreatedEvent(OrderCreatedEvent event) {
        User retrieved = userDao.findByUserName( event.getOrderDto().getUserDto().getFullName() );

        Card card = retrieved.getCreditCards().stream()
                .filter( c -> c.getCreditCardNumber() == Long.parseLong( event.getOrderDto().getCreditCardNumber() ) )
                .findFirst()
                .get();


        card.blockFunds( Double.parseDouble( event.getOrderDto().getBlockedFunds() ) );
        

    }


}
