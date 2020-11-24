package ru.rambler.alexeimohov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.rambler.alexeimohov.dao.interfaces.UserDao;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.CardMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.MessageMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.entities.User;
import ru.rambler.alexeimohov.service.events.OrderFinishedEvent;
import ru.rambler.alexeimohov.service.interfaces.IUserService;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class UserService implements IUserService {

    private UserDao userDao;

    private UserMapper userMapper;

    private CardMapper cardMapper;

    private MessageMapper messageMapper;

    public UserService(UserDao userDao, UserMapper userMapper, CardMapper cardMapper, MessageMapper messageMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.cardMapper = cardMapper;
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
            log.debug( "user has been saved: " + user.getFullName() );
        } else {
            userDao.update( user );
            log.debug( "user has been updated: " + user.getFullName() );
        }
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
    public void onApplicationEvent(OrderFinishedEvent event) {
        User user = userDao.findById( Long.valueOf( event.getOrderDto().getUserDto().getId() ) );
        log.info( "triggered by order service : " + user.getFullName() + " " + user.getCreditCards().size() );
    }
}
