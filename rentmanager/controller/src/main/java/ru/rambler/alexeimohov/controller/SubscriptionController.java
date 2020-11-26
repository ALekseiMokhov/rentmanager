package ru.rambler.alexeimohov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.SubscriptionDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.service.interfaces.ISubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private ISubscriptionService subscriptionService;

    public SubscriptionController(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/")
    public ResponseEntity createSubscription(@RequestBody SubscriptionDto dto) {
        subscriptionService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @GetMapping("/{id}")
    public SubscriptionDto getById(@PathVariable long id) {
        return subscriptionService.getById( id );
    }

    @GetMapping("/name/{id}")
    public UserDto getHolder(@PathVariable long id) {
        return subscriptionService.getSubscriptionHolder( id );
    }

    @PutMapping("/")
    public ResponseEntity updateSubscription(@RequestBody SubscriptionDto dto) {
        subscriptionService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSubscription(@PathVariable long id) {
        subscriptionService.remove( id );
        return new ResponseEntity( HttpStatus.OK );
    }

    @GetMapping("/")
    public List <SubscriptionDto> getAllSubscriptions() {
        return subscriptionService.getAll();
    }
}
