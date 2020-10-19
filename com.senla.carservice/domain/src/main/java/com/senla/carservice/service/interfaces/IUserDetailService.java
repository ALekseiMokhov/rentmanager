package com.senla.carservice.service.interfaces;

import com.senla.carservice.entity.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDetailService extends UserDetailsService {
    void saveUser(User user);
    void deleteUser(User user);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
