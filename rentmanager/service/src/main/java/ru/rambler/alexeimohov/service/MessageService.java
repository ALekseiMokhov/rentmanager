package ru.rambler.alexeimohov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;
import ru.rambler.alexeimohov.service.interfaces.IMessageService;

import java.time.LocalDateTime;
import java.util.List;

/*TODO : refactoring - make Message entity extends SimpleMailMessage */
@Service
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MessageService implements IMessageService {

    private MessageDao messageDao;

    private MessageMapper messageMapper;

    private UserMapper userMapper;

    private JavaMailSender javaMailSender;

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
        if (message.getId() == null) {
            messageDao.save( message );
        } else messageDao.update( message );
    }

    @Override
    @TransactionalEventListener
    @Transactional(readOnly = false)
    public void sendMessage(OrderFinishedEvent event) {
        OrderDto dto = event.getOrderDto();
        Message message = new Message();

        message.setDateTimeOfSending( LocalDateTime.now() );
        message.setUser( userMapper.fromDto( dto.getUserDto() ) );

        message.setFrom( "alexeimohov@rambler.ru" );
        message.setTo( dto.getUserDto().getEmail() );
        message.setSubject( "Order " + dto.getId() + " " + dto.getStatus() );
        message.setText( String.format( """
                 Hello, %s!Thanks for choosing our service!
                 Your total price is %s .
                 Hope to see you again!
                """, dto.getUserDto().getFullName(), dto.getTotalPrice() ) );

        javaMailSender.send( message );
        messageDao.save( message );
        log.info( "Message sent!" );
    }

    @Override
    public void sendCustomMessage(UserDto to, String topic, String text) {
        Message message = new Message();
        message.setUser( userMapper.fromDto( to ) );
        message.setText( text );
        /*TODO fix*/
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
