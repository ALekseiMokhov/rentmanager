package ru.rambler.alexeimohov.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rambler.alexeimohov.dto.SubscriptionDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.service.interfaces.ISubscriptionService;

import java.util.List;
  /*
  * Controller linked to ISubscriptionService */
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private ISubscriptionService subscriptionService;

    public SubscriptionController(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{id}")
    public SubscriptionDto getById(@PathVariable long id) {
        return subscriptionService.getById( id );
    }

    @GetMapping("/is-valid/{id}")
    public Boolean isValid(@PathVariable long id) {
        return subscriptionService.isValid( id );
    }

    @GetMapping("/name/{id}")
    public UserDto getHolder(@PathVariable long id) {
        return subscriptionService.getSubscriptionHolder( id );
    }


    @GetMapping("/")
    public List <SubscriptionDto> getAllSubscriptions() {
        return subscriptionService.getAll();
    }
}
