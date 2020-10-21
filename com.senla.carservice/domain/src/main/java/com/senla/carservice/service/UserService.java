package com.senla.carservice.service;

import com.senla.carservice.entity.user.User;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;


@Service
@Slf4j
@Transactional
public class UserService implements IUserService {
    @Autowired
    @Qualifier("userJpaRepository")
    private IGenericRepository<User> repository;


    @Override
    public Boolean isPresent(String name) {
        return this.repository.findAll().stream()
                .anyMatch(u->u.getName().equals(name));

    }

    public User loadUserByUsername(String username) {
        User user = null;
        try {
            user = this.repository.findAll()
                    .stream()
                    .filter(u -> u.getName().equals(username))
                    .findFirst()
                    .get()
            ;
        } catch (NoSuchElementException e) {
            log.error("no such user: " + username);
        }
        log.debug("User was found: " + user);
        return user;
    }


    public User findById(UUID id) {
        return this.repository.getById(id);
    }

    public User saveUser(User user) {
        user.setPassword(user.getPassword());
        this.repository.save(user);
        log.info(user.getName() + " has been successfully saved");
        return user;
    }

    public void updateUser(User user) {
        this.repository.update(user);
    }

    public void deleteUser(User user) {
        this.repository.delete(user.getId());
    }
}
