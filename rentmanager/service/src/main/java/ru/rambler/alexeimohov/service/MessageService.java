package ru.rambler.alexeimohov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.rambler.alexeimohov.dao.interfaces.MessageDao;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.MessageMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.entities.Message;
import ru.rambler.alexeimohov.entities.User;
import ru.rambler.alexeimohov.service.events.OrderCreatedEvent;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;
import ru.rambler.alexeimohov.service.events.SubscriptionSetEvent;
import ru.rambler.alexeimohov.service.events.UserRegisteredEvent;
import ru.rambler.alexeimohov.service.interfaces.IMessageService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MessageService implements IMessageService {

    private MessageDao messageDao;

    private MessageMapper messageMapper;

    private UserMapper userMapper;

    private JavaMailSender javaMailSender;

    private ApplicationEventPublisher publisher;

    @Value("${spring.mail.username}")
    private String username;
    @Value("${greeting.text}")
    private String greetingText;
    @Value("${order.finished.text}")
    private String orderFinishedText;
    @Value("${order.created.text}")
    private String orderCreatedText;
    @Value("${subscription.ordered.text}")
    private String subscriptionOrdered;

    public MessageService(MessageDao messageDao, MessageMapper messageMapper,
                          UserMapper userMapper, JavaMailSender javaMailSender, ApplicationEventPublisher publisher) {
        this.messageDao = messageDao;
        this.messageMapper = messageMapper;
        this.userMapper = userMapper;
        this.javaMailSender = javaMailSender;
        this.publisher = publisher;
    }


    @Override
    @TransactionalEventListener
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Async
    public void sendMessageAfterCompleteOrder(OrderFinishedEvent event) {
        OrderDto dto = event.getOrderDto();
        Message message = new Message();

        message.setDateTimeOfSending( LocalDateTime.now() );
        message.setUser( userMapper.fromDto( dto.getUserDto() ) );

        message.setFrom( this.username );
        message.setTo( dto.getUserDto().getEmail() );
        message.setSubject( "Order " + dto.getId() + " " + dto.getStatus() );
        message.setText( String.format( orderFinishedText, dto.getUserDto().getUsername(), dto.getTotalPrice() ) );


        javaMailSender.send( message );
        messageDao.save( message );
        log.debug( "Message after order finished event sent!" );
    }

    @Override
    @TransactionalEventListener
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Async
    public void greetNewUser(UserRegisteredEvent event) {
        UserDto dto = event.getUserDto();
        Message message = new Message();

        message.setDateTimeOfSending( LocalDateTime.now() );
        message.setUser( userMapper.fromDto( dto ) );
        message.setFrom( this.username );
        message.setTo( dto.getEmail() );
        message.setSubject( "User registered " + dto.getId() );
        message.setText( String.format( greetingText, dto.getUsername() ) );

        javaMailSender.send( message );
        messageDao.save( message );
        log.debug( "Message to new user has been sent!" );
    }

    @Override
    @TransactionalEventListener
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Async
    public void sendMessageAfterCreateOrder(OrderCreatedEvent event) {
        UserDto dto = event.getOrderDto().getUserDto();
        User user = userMapper.fromDto( dto );
        Message message = new Message();

        message.setUser( user );
        message.setFrom( this.username );
        message.setTo( user.getEmail() );
        message.setSubject( "Order booked: " + event.getOrderDto().getId() );
        message.setText( String.format( orderCreatedText, dto.getUsername(), event.getOrderDto().getId() ) );

        javaMailSender.send( message );
        messageDao.save( message );
        log.debug( "Message after new order creation has been sent!" );
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Async
    public void sendCustomMessage(UserDto dto, String topic, String text) {
        User user = userMapper.fromDto( dto );
        Message message = new Message();

        message.setUser( user );
        message.setText( text );
        message.setFrom( this.username );
        message.setTo( user.getEmail() );
        message.setSubject( topic );

        messageDao.save( message );
    }

    @Override
    @TransactionalEventListener
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Async
    public void sendMessageAfterSetSubscription(SubscriptionSetEvent event) {
        UserDto dto = event.getSubscriptionDto().getUser();
        User user = userMapper.fromDto( dto );
        Message message = new Message();

        message.setUser( user );
        message.setFrom( this.username );
        message.setTo( user.getEmail() );
        message.setSubject( "Subscription ordered: " + event.getSubscriptionDto().getId() );
        message.setText( String.format( subscriptionOrdered, dto.getUsername(),
                event.getSubscriptionDto().getStartDate(), event.getSubscriptionDto().getPrice() ) );

        javaMailSender.send( message );
        messageDao.save( message );
        log.debug( "Message after new order creation has been sent!" );
    }

    @Override
    public MessageDto getById(Long id) {
        return messageMapper.toDto( messageDao.findById( id ) );
    }

    @Override
    public List <MessageDto> getAll() {
        return messageMapper.listToDto( messageDao.findAll() );
    }


}
