package com.senla.carservice.controller.rest;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.controller.OrderController;
import com.senla.carservice.controller.PlaceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private PlaceController placeController;
    @Autowired
    private MasterController masterController;
    @Autowired
    private OrderController orderController;

    @GetMapping
    public String printHomePage(ModelMap model) {

        model.addAttribute( "name", "Visitor" );
        model.addAttribute( "message", "Car service dashboard" );

        return "home";


    }

}
