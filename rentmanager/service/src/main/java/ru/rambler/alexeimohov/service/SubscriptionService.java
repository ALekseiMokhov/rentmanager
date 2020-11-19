package ru.rambler.alexeimohov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.SubscriptionDao;
import ru.rambler.alexeimohov.dto.SubscriptionDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.SubscriptionMapper;
import ru.rambler.alexeimohov.entities.Subscription;
import ru.rambler.alexeimohov.service.interfaces.ISubscriptionService;

import java.util.List;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class SubscriptionService implements ISubscriptionService {

    private SubscriptionDao subscriptionDao;

    private SubscriptionMapper subscriptionMapper;

    public SubscriptionService(SubscriptionDao subscriptionDao, SubscriptionMapper subscriptionMapper) {
        this.subscriptionDao = subscriptionDao;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public SubscriptionDto getById(Long id) {
        return subscriptionMapper.toDto( subscriptionDao.findById( id ) );
    }

    @Override
    public void saveOrUpdate(SubscriptionDto dto) {
        Subscription subscription = subscriptionMapper.fromDto( dto );
        if(subscriptionDao.findById( subscription.getId() )==null){
            subscriptionDao.save( subscription );
        }
        else {
            subscriptionDao.update( subscription );
        }
    }

    @Override
    public void remove(Long id) {
        subscriptionDao.remove( id );
    }

    @Override
    public List <SubscriptionDto> getAll() {
        return subscriptionMapper.listToDto( subscriptionDao.findAll() );
    }

    @Override
    public String getSubscriptionHolder(Long id) {
        return subscriptionDao.getSubscribeHolder( id ).getFullName();
    }
}
