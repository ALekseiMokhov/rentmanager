package ru.rambler.alexeimohov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.SubscriptionDao;
import ru.rambler.alexeimohov.dto.SubscriptionDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.SubscriptionMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.entities.Subscription;
import ru.rambler.alexeimohov.service.interfaces.ISubscriptionService;

import java.util.List;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class SubscriptionService implements ISubscriptionService {

    private SubscriptionDao subscriptionDao;

    private SubscriptionMapper subscriptionMapper;

    private UserMapper userMapper;

    public SubscriptionService(SubscriptionDao subscriptionDao, SubscriptionMapper subscriptionMapper, UserMapper userMapper) {
        this.subscriptionDao = subscriptionDao;
        this.subscriptionMapper = subscriptionMapper;
        this.userMapper = userMapper;
    }

    @Override
    public SubscriptionDto getById(long id) {
        return subscriptionMapper.toDto( subscriptionDao.findById( id ) );
    }

    @Transactional(readOnly = false)
    @Override
    public void saveOrUpdate(SubscriptionDto dto) {
        Subscription subscription = subscriptionMapper.fromDto( dto );
        if (subscription.getId() == null) {
            subscriptionDao.save( subscription );
        } else {
            subscriptionDao.update( subscription );
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(long id) {
        subscriptionDao.remove( id );
    }

    @Override
    public List <SubscriptionDto> getAll() {
        return subscriptionMapper.listToDto( subscriptionDao.findAll() );
    }

    @Override
    public UserDto getSubscriptionHolder(long id) {
        return userMapper.toDto( subscriptionDao.getSubscribeHolder( id ) );
    }

    public boolean isExpired(long id){
        return subscriptionDao.isExpired( id );
    }

}
