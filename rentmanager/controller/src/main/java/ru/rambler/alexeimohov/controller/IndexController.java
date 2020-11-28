package ru.rambler.alexeimohov.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String greeting() {
        return "Greetings from server! Go to login or reg!";
    }

    @PostMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/registration")
    public String registration() {
        return "registration";

    }
}
