package ru.rambler.alexeimohov.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Basic information set in properties*/
@RestController
@RequestMapping("/")
public class IndexController {

    /*@param injected by Spring*/
    @Value("${index.page.text}")
    private String greeting;

    @GetMapping
    public String greeting() {
        return greeting;
    }

}
