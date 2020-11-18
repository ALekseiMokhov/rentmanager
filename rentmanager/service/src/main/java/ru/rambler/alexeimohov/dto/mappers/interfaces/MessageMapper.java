package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.mappers.DateMapper;
import ru.rambler.alexeimohov.entities.Message;

import java.util.List;

@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface MessageMapper {
    @Mapping(source = "user.id", target = "userId")
    MessageDto toDto(Message message);

    @Mapping(source = "userId", target = "user.id")
    Message fromDto(MessageDto dto);

    List <MessageDto> listToDto(List <Message> list);

    List <Message> listFromDto(List <MessageDto> list);
}
