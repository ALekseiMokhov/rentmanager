package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rambler.alexeimohov.dto.SubscriptionDto;
import ru.rambler.alexeimohov.entities.Subscription;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    @InheritInverseConfiguration
    Subscription fromDto(SubscriptionDto dto);

    @Mapping(source = "user.fullName", target = "userName")
    SubscriptionDto toDto(Subscription subscription);

    List <Subscription> listFromDto(List <SubscriptionDto> list);

    List <SubscriptionDto> listToDto(List <Subscription> list);
}
