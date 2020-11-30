package ru.rambler.alexeimohov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.rambler.alexeimohov.dao.interfaces.MessageDao;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.MessageMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.entities.Message;
import ru.rambler.alexeimohov.service.events.OrderCreatedEvent;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;
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

    @Value("${spring.mail.username}")
    private String username;
    @Value("${greeting.text}")
    private String greetingText;
    @Value("${order.finished.text}")
    private String orderFinishedText;
    @Value("${order.created.text}")
    private String orderCreatedText;

    public MessageService(MessageDao messageDao, MessageMapper messageMapper, UserMapper userMapper, JavaMailSender javaMailSender) {
        this.messageDao = messageDao;
        this.messageMapper = messageMapper;
        this.userMapper = userMapper;
        this.javaMailSender = javaMailSender;
    }

    @Transactional(readOnly = false)
    @Override
    public void saveOrUpdate(MessageDto dto) {
        Message message = messageMapper.fromDto( dto );
        log.debug( "msg created from dto: "+ message.getId()+" __"+message.getText());
        if (message.getId() == null) {
            messageDao.save( message );
        } else messageDao.update( message );
    }

    @Override
    @TransactionalEventListener
    @Transactional(readOnly = false)
    @Async
    public void sendMessageAfterCompleteOrder(OrderFinishedEvent event) {
        OrderDto dto = event.getOrderDto();
        Message message = new Message();

        message.setDateTimeOfSending( LocalDateTime.now() );
        message.setUser( userMapper.fromDto( dto.getUserDto() ) );

        message.setFrom(  this.username);
        message.setTo( dto.getUserDto().getEmail() );
        message.setSubject( "Order " + dto.getId() + " " + dto.getStatus() );
        message.setText( String.format( orderFinishedText, dto.getUserDto().getFullName(), dto.getTotalPrice() ) );

        javaMailSender.send( message );
        messageDao.save( message );
        log.info( "Message sent!" );
    }

    @Override
    @TransactionalEventListener
    @Transactional(readOnly = false)
    @Async
    public void greetNewUser(UserRegisteredEvent event) {
        UserDto dto = event.getUserDto();
        Message message = new Message();

        message.setDateTimeOfSending( LocalDateTime.now() );
        message.setUser( userMapper.fromDto( dto ));
        message.setFrom( this.username );
        message.setTo( dto.getEmail() );
        message.setSubject( "User registered " + dto.getId()  );
        message.setText( String.format( greetingText, dto.getFullName() ));

        javaMailSender.send( message );
        messageDao.save( message );
        log.debug( "Message to new user has been sent!" );
    }
    @Override
    @TransactionalEventListener
    @Async
    @Transactional(readOnly = false)
    public void sendMessageAfterCreateOrder(OrderCreatedEvent event) {
        UserDto dto = event.getOrderDto().getUserDto();
        Message message = new Message();

        message.setDateTimeOfSending( LocalDateTime.now() );
        message.setUser( userMapper.fromDto( dto ));
        message.setFrom( this.username );
        message.setTo( dto.getEmail() );
        message.setSubject( "Order booked: " + dto.getId()  );
        message.setText( String.format( orderCreatedText, dto.getFullName(),dto.getId() ));

        javaMailSender.send( message );
        messageDao.save( message );
        log.debug( "Message to new user has been sent!" );
    }

    @Override
    @Transactional(readOnly = false)
    @Async
    public void sendCustomMessage(UserDto to, String topic, String text) {
        Message message = new Message();
        message.setUser( userMapper.fromDto( to ) );
        message.setText( text );
        message.setFrom( this.username );
        message.setTo( to.getEmail() );
        message.setSubject( topic );
        message.setDateTimeOfSending( LocalDateTime.now() );
        messageDao.save( message );
    }

    @Override
    public MessageDto getById(Long id) {
        return messageMapper.toDto( messageDao.findById( id ) );
    }

    @Override
    public List <MessageDto> getAll() {
        return messageMapper.listToDto( messageDao.findAll() );
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(Long id) {
        messageDao.remove( id );
    }


}
