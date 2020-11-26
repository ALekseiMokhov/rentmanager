package ru.rambler.alexeimohov.service.interfaces;

import org.springframework.stereotype.Service;
import ru.rambler.alexeimohov.dto.SubscriptionDto;
import ru.rambler.alexeimohov.dto.UserDto;

import java.util.List;

@Service
public interface ISubscriptionService {

    SubscriptionDto getById(long id);

    void saveOrUpdate(SubscriptionDto dto);

    void remove(long id);

    List <SubscriptionDto> getAll();

    UserDto getSubscriptionHolder(long id);
}
