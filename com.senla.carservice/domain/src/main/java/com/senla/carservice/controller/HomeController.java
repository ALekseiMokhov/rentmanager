package com.senla.carservice.controller;

import com.senla.carservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/")
@Profile("rest")
public class HomeController {
    @Value("${home.message}")
    private String message;
    @GetMapping("")
    public String showRegistrationForm() {
        return message;
    }
}
