package com.senla.carservice.controller;

import com.senla.carservice.dto.UserDto;
import com.senla.carservice.entity.user.Role;
import com.senla.carservice.entity.user.User;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.security.jwt.JwtAuthenticationResponse;
import com.senla.carservice.security.jwt.JwtTokenProvider;
import com.senla.carservice.service.UserService;
import com.senla.carservice.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/")
@Profile("rest")
public class UserRestController {
    @Autowired
    private IUserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Value("${home.message}")
    private String message;

    /* endpoints*/

    @GetMapping("")
    public String showRegistrationForm() {
        return message;
    }

    @PostMapping("/register/{name}/{pass}/{role}")
    public ResponseEntity register(@PathVariable @Min(4) String name,
                           @PathVariable @Min(5) String pass,
                           @PathVariable @NotEmpty String role) {
        if(this.userService.loadUserByUsername(name)!=null){
            return new ResponseEntity("User already exists! ",
                    HttpStatus.BAD_REQUEST);
        }
        User user = User.builder()
                .name(name)
                .password(pass)
                .roles(Set.of(Role.valueOf(role)))
                .build();
        this.userService.saveUser(user);
        return new ResponseEntity("new User registered: " + name,HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDto userDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getName(),
                        userDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

}
