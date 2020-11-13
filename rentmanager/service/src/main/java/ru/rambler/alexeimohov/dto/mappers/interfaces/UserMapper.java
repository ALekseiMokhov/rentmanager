package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Mapper;
import ru.rambler.alexeimohov.dto.mappers.GeometryMapper;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.entities.User;
@Mapper(componentModel = "spring", uses = GeometryMapper.class)
public interface UserMapper extends GenericMapper <User, UserDto> {
}
