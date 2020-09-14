package com.senla.carservice.controller.rest;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.controller.PlaceController;
import com.senla.carservice.entity.garage.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/place_menu")
public class PlaceMenuController {

    @Autowired
    private PlaceController placeController;


    @GetMapping
    public String showPlaces(ModelMap model){
     model.put( "places", this.placeController.getPlaces() ) ;
    return "place_menu";
    }

    @PostMapping()
    public String add(@RequestParam(required = false) Integer numberOfPlaces , ModelMap model){
        this.placeController.addPlaces( numberOfPlaces  );
        model.addAttribute( "places" ,this.placeController.getPlaces())    ;
      return "place_menu";
    }
    @PostMapping ("setDate")
    public String setDate(@ModelAttribute Place place, ModelMap model) {
        System.out.println(place.getId());
        this.placeController.savePlace( place.getId() );
        model.addAttribute( "places" ,this.placeController.getPlaces())    ;
        return  "place_menu";
    }
}
