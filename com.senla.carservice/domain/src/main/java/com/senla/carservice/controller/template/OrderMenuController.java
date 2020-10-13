package com.senla.carservice.controller.template;

import com.senla.carservice.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/*
 */@Controller
@RequestMapping("/orders")
@Profile("ui")
public class OrderMenuController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public String showAll(ModelMap model){
        model.addAttribute( "message", this.orderService.getOrders());
        return "orders";
    }
    @GetMapping("/add")
    public String addOrder(@RequestParam(required = false)String startOfExecution,
                           @RequestParam(required = false) Set<String> specialities,
                            ModelMap model){
        model.addAttribute( "message", this.orderService.getOrders());
        return "orders";
    }
}
