package com.senla.carservice.service.interfaces;

import com.senla.carservice.dto.UserDto;
import com.senla.carservice.entity.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface IUserService extends UserDetailsService {
    User loadUserByUsername(String username);

    User findById(UUID id);

    User saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    Boolean isPresent(String name);

    List <UserDto> getUsers();

}
