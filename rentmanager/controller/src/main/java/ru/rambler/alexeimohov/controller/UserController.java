package ru.rambler.alexeimohov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.SubscriptionDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.service.interfaces.IUserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity createUser(@Valid @RequestBody UserDto dto) {
        userService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable long id) {
        return userService.getById( id );
    }

    @GetMapping("/{name}")
    public UserDto getByUserName(@PathVariable String name) {
        return userService.getByUserName( name );
    }

    @PutMapping("/")
    public ResponseEntity updateUser(@Valid @RequestBody UserDto dto) {
        userService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.OK );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable long id) {
        userService.remove( id );
        return new ResponseEntity( HttpStatus.OK );

    }

    @PatchMapping("/card/{id}")
    public ResponseEntity addCard(@PathVariable long id, @Valid @RequestBody CardDto cardDto) {
        userService.addCreditCard( id, cardDto );
        return new ResponseEntity( HttpStatus.CREATED );
    }
    @PatchMapping("/subscription/")
    public ResponseEntity setSubscription( @Valid @RequestBody SubscriptionDto subscriptionDto) {
        userService.setSubscription( subscriptionDto );
        return new ResponseEntity( HttpStatus.CREATED );
    }
    @PatchMapping("/subscription/{userId}")
    public ResponseEntity removeSubscription(@PathVariable long userId ) {
        userService.removeSubscription( userId );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @PatchMapping("/card/{id}/delete")
    public ResponseEntity removeCard(@PathVariable long id, @Valid @RequestBody CardDto cardDto) {
        userService.removeCreditCard( id, cardDto );
        return new ResponseEntity( HttpStatus.CREATED );
    }
    @PatchMapping("/message/{id}")
    public ResponseEntity addMessage(@PathVariable long id,@RequestBody MessageDto messageDto) {
        userService.addMessage( id, messageDto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @PatchMapping("/message/{id}/delete")
    public ResponseEntity removeMessage(@PathVariable long id, @Valid @RequestBody MessageDto messageDto) {
        userService.removeMessage( id, messageDto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @GetMapping("/")
    public List <UserDto> getAll() {
        return userService.getAll();
    }
}
