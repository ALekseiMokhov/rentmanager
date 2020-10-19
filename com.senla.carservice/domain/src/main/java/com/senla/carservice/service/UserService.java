package com.senla.carservice.service;

import com.senla.carservice.entity.user.User;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.service.interfaces.IUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService implements IUserDetailService, UserDetailsService {
    @Autowired
    @Qualifier("userJpaRepository")
    private IGenericRepository<User> repository;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=null;
        try{
        user = this.repository.findAll()
                .stream()
                .filter(u -> u.getName().equals(username) )
                .findFirst()
                .get()
                ;}
        catch (NoSuchElementException e){

        }
        if (user==null){
            throw new UsernameNotFoundException(username);
        }
        return user;
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
