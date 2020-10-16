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

    @PostMapping("/register/{name}/{pass}/{role}/{email}")
    public String register(@PathVariable @Min(4) String name,
                           @PathVariable @Min(5) String pass,
                            @PathVariable @NotEmpty String role,
                            @PathVariable @Email String email){
       User user = User.builder()
               .name(name)
               .password(pass)
               .role(Role.valueOf(role))
               .email(email)
               .build();
     /*  if(this.userDetailService.loadUserByUsername(name)!=null){
           throw new ExistedUserException("User already exists!");

           TODO fix NoSuchElEx
       }*/
        this.userDetailService.saveUser(user);
        return "new User added: " + name ;
    }
    /*@PostMapping("/login/{name}/{pass}")
    public String login(@PathVariable @NotEmpty String name, @PathVariable @NotEmpty String pass){
      User user = (User) this.userDetailService.loadUserByUsername(name);
       if(user.getPassword().equals(pass)==false){
           throw new MalformedLoginDataException(" Incorrect password input!");
       }
        if(log.isDebugEnabled()){
           log.debug(name + " was successfully logged in");
       }
        return "user successfully logged in: "+name;
    }*/

}
