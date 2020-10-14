package com.senla.carservice.dto.mappers.interfaces;

import com.senla.carservice.dto.UserDto;
import com.senla.carservice.dto.mappers.RoleMapper;
import com.senla.carservice.entity.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = RoleMapper.class, componentModel = "spring")
public interface UserMapper {

    UserDto fromUser(User user);

    User fromDto(UserDto dto);

    List<UserDto> userListToDto(List<User> users);

    List<User> dtoToUsers(List<UserDto> dto);
}
