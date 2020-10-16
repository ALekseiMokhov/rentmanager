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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserService implements IUserDetailService {
    @Autowired
    @Qualifier("userJpaRepository")
    private IGenericRepository<User> repository;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> list = this.repository.findAll();
        return list.stream()
                .filter(u -> u.getName() == username)
                .findFirst()
                .get();

    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public void updateUser(User user) {
        this.repository.update(user);
    }

    public void deleteUser(User user) {
        this.repository.delete(user.getId());
    }
}
