package com.senla.carservice.controller.template;

import com.senla.carservice.controller.MasterRestController;
import com.senla.carservice.controller.OrderRestController;
import com.senla.carservice.controller.PlaceRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Profile("ui")
public class HomeController {
    @Autowired
    private PlaceMenuController placeController;
    @Autowired
    private MasterMenuController masterController;
    @Autowired
    private OrderMenuController orderController;

    @GetMapping
    public String printHomePage(ModelMap model) {

        model.addAttribute( "name", "Visitor" );
        model.addAttribute( "message", "Car service dashboard" );

        return "home";


    }

}
