package com.senla.carservice.controller;

import com.senla.carservice.entity.user.Role;
import com.senla.carservice.entity.user.User;
import com.senla.carservice.security.exceptions.ExistedUserException;
import com.senla.carservice.security.exceptions.MalformedLoginDataException;
import com.senla.carservice.service.interfaces.IUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
@Slf4j
@RestController
@RequestMapping("/")
@Profile("rest")
public class UserRestController {
    @Autowired
    @Qualifier("userService")
    private IUserDetailService userDetailService;
    @Value("${home.message}")
    private String message;
    @GetMapping("")
    public String showRegistrationForm() {
        return message;
    }

    @PostMapping("/register/{name}/{pass}/{role}")
    public String register(@PathVariable @Min(4) String name,
                           @PathVariable @Min(5) String pass,
                            @PathVariable @NotEmpty String role){
       User user = User.builder()
               .name(name)
               .password(pass)
               .role(Role.valueOf(role))
               .build();
        this.userDetailService.saveUser(user);
        return "new User added: " + name ;
    }

    @GetMapping("/get/{name}")
    public String getUser(@PathVariable String name){

        User user = (User) this.userDetailService.loadUserByUsername(name);
        return user.getName() + " "+user.getRole() +" "+user.getId()+" "+user.getAuthorities();
    }
}
