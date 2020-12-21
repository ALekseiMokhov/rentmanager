package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Mapper;
import ru.rambler.alexeimohov.dto.SubscriptionDto;
import ru.rambler.alexeimohov.entities.Subscription;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface SubscriptionMapper {

    Subscription fromDto(SubscriptionDto dto);

    SubscriptionDto toDto(Subscription subscription);

    List <Subscription> listFromDto(List <SubscriptionDto> list);

    List <SubscriptionDto> listToDto(List <Subscription> list);
}
