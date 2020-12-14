package ru.rambler.alexeimohov.controller;

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
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.SubscriptionDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.jwt.JwtAuthenticationResponse;
import ru.rambler.alexeimohov.jwt.JwtTokenProvider;
import ru.rambler.alexeimohov.service.interfaces.IUserService;

import javax.validation.Valid;
import java.util.List;
/*
*
* Controller responsible for registering, authentication, authorization and CRUD operations on ru.rambler.alexeimohov.entities.User
* Sets @param Card and @param Subscription for User*/
@RestController
@RequestMapping("/users")
public class UserController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder;

    private IUserService userService;

    public UserController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder, IUserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody UserDto dto) {
        dto.setPassword( passwordEncoder.encode( dto.getPassword() ) );
        userService.saveOrUpdate( dto );
        return new ResponseEntity( "new User registered: " + dto.getUsername(), HttpStatus.CREATED );
    }


    @PostMapping("/signin")
    public ResponseEntity <?> authenticateUser(@RequestBody UserDto userDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication( authentication );

        String jwt = tokenProvider.generateToken( authentication );
        return ResponseEntity.ok( new JwtAuthenticationResponse( jwt ) );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public UserDto getById(@PathVariable long id) {
        return userService.getById( id );
    }

    @GetMapping("/name")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public UserDto getByUserName(@RequestParam String name) {
        return userService.getByUserName( name );
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity updateUser(@Valid @RequestBody UserDto dto) {
        userService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.OK );

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity deleteUser(@PathVariable long id) {
        userService.remove( id );
        return new ResponseEntity( HttpStatus.OK );

    }

    @PatchMapping("/card/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity addCard(@PathVariable long userId, @Valid @RequestBody CardDto cardDto) {
        userService.addCreditCard( userId, cardDto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @PatchMapping("/subscription/")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity setSubscription(@Valid @RequestBody SubscriptionDto subscriptionDto) {
        userService.setSubscription( subscriptionDto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @PatchMapping("/subscription/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity removeSubscription(@PathVariable long userId) {
        userService.removeSubscription( userId );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @PatchMapping("/card/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity removeCard(@PathVariable long id, @Valid @RequestBody CardDto cardDto) {
        userService.removeCreditCard( id, cardDto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @PatchMapping("/message/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity addMessage(@PathVariable long id, @RequestBody MessageDto messageDto) {
        userService.addMessage( id, messageDto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @PatchMapping("/message/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity removeMessage(@PathVariable long id, @Valid @RequestBody MessageDto messageDto) {
        userService.removeMessage( id, messageDto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public List <UserDto> getAll() {
        return userService.getAll();
    }
}
