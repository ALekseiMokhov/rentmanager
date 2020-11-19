package ru.rambler.alexeimohov.service.interfaces;

import ru.rambler.alexeimohov.dto.UserDto;

import java.util.List;

public interface IUserService {

    UserDto getById(Long id);

    void remove(Long id);

    List <UserDto> getAll();

    UserDto getByUserName(String userName);

    void saveOrUpdate(UserDto dto);
}
