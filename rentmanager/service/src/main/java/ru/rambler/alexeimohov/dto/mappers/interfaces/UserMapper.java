package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.mappers.CycleAvoidingMappingContext;
import ru.rambler.alexeimohov.dto.mappers.GeometryConverter;
import ru.rambler.alexeimohov.entities.User;

@Mapper(componentModel = "spring", uses = { GeometryConverter.class, MessageMapper.class })
public interface UserMapper {
    UserDto toDto(User user, @Context CycleAvoidingMappingContext context);

    User fromDto(UserDto dto, @Context CycleAvoidingMappingContext context);
}
