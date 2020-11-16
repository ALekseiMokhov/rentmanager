package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.mappers.CycleAvoidingMappingContext;
import ru.rambler.alexeimohov.entities.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageDto messageToDto(Message message, @Context CycleAvoidingMappingContext context);

    Message DtoToMessage(MessageDto dto, @Context CycleAvoidingMappingContext context);
}
