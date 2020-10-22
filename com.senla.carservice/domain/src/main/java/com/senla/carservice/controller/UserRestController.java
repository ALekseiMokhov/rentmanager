package com.senla.carservice.controller;

import com.senla.carservice.dto.UserDto;
import com.senla.carservice.entity.user.User;
import com.senla.carservice.security.jwt.JwtAuthenticationResponse;
import com.senla.carservice.security.jwt.JwtTokenProvider;
import com.senla.carservice.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
@Profile("rest")
public class UserRestController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    private IUserService userService;
    @Value("${home.message}")
    private String message;

    /* endpoints*/

    @GetMapping("")
    public String showRegistrationForm() {
        return message;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(userDto.getRoles())
                .build();
        if (  this.userService.isPresent(user.getName())) {
            return new ResponseEntity("User already exists! ",
                    HttpStatus.BAD_REQUEST);
        }

        this.userService.saveUser(user);
        return new ResponseEntity("new User registered: " + user.getName(), HttpStatus.CREATED);
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

    @Secured("ROLE_ADMIN")
    @GetMapping("/users")
    public List <UserDto> getUsers()  {
         return this.userService.getUsers();
    }

}
