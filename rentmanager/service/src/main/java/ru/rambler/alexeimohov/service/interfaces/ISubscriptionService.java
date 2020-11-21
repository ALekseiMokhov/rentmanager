package ru.rambler.alexeimohov.service.interfaces;

import ru.rambler.alexeimohov.dto.SubscriptionDto;

import java.util.List;

public interface ISubscriptionService {

    SubscriptionDto getById(Long id);

    void saveOrUpdate(SubscriptionDto dto);

    void remove(Long id);

    List <SubscriptionDto> getAll();

    String getSubscriptionHolder(Long id);
}
