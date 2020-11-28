package ru.rambler.alexeimohov.dto.mappers.interfaces;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.mappers.GeometryConverter;
import ru.rambler.alexeimohov.entities.User;

import java.util.List;

@Mapper(componentModel = "spring", uses = { GeometryConverter.class, MessageMapper.class })
public interface UserMapper {


    @BeforeMapping
    default void beforeMapping(@MappingTarget UserDto target, User source) {
        if(source.getSubscription() ==null){
             target.setHasValidSubscription( "false" );
        }
        else if (source.getSubscription().isExpired()) {
            target.setHasValidSubscription( "false" );
        }
    }
    @Mapping( source = "password",target = "password",ignore = true )
    UserDto toDto(User user);

    User fromDto(UserDto dto);


    List <User> listFromDto(List <UserDto> list);

    List <UserDto> listToDto(List <User> list);
}
