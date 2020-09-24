package com.senla.carservice.controller.rest;

import com.senla.carservice.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping("/order_menu")
public class OrderMenuController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public String showAll(ModelMap model) {
        model.addAttribute( "orders", this.orderService.getOrders() );
        return "order_menu";
    }

    @PostMapping("/addOrder")
    public String addOrder(@RequestParam(required = false) String startOfExecution,
                           @RequestParam(required = false) Set <String> specialities,
                           ModelMap model) {
        model.addAttribute( "orders", this.orderService.getOrders() );
        model.addAttribute( "message", "order has been created!" );
        return "order_menu";
    }
}
