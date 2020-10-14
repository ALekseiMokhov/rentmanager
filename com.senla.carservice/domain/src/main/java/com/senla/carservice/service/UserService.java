package com.senla.carservice.service;

import com.senla.carservice.dto.UserDto;
import com.senla.carservice.dto.mappers.interfaces.UserMapper;
import com.senla.carservice.entity.user.User;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.repository.jpa.GenericJpaRepository;
import com.senla.carservice.service.interfaces.IUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserDetailService {
    @Autowired
    @Qualifier("userJpaRepository")
    private IGenericRepository<User> repository;
    @Autowired
    UserMapper userMapper;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> list = this.repository.findAll();
        return list.stream()
                .filter(u -> u.getName() == username)
                .findFirst()
                .get();

    }
    public UserDto getUserDtoByName(String name){
     return this.userMapper.fromUser((User) loadUserByUsername(name))  ;
    }
}
