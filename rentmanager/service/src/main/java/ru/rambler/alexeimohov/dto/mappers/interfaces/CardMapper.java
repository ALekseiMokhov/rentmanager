package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.entities.Card;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CardMapper {
    @Mapping(source = "user.id", target = "userId")
    CardDto toDto(Card card);

    @Mapping(source="userId", target = "user.id")
    Card fromDto(CardDto dto);

    List <Card> listFromDto(List <CardDto> list);

    List <CardDto> listToDto(List <Card> list);
}
