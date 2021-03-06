package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.mappers.GeometryConverter;
import ru.rambler.alexeimohov.entities.User;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GeometryConverter.class, MessageMapper.class})
public interface UserMapper {


    @Mapping(source = "password", target = "password", ignore = true)
    UserDto toDto(User user);

    User fromDto(UserDto dto);


    List<User> listFromDto(List<UserDto> list);

    List<UserDto> listToDto(List<User> list);
}
