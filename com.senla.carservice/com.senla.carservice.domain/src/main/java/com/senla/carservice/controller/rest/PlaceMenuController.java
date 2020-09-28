package com.senla.carservice.controller.rest;

import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.service.IPlaceService;
import com.senla.carservice.service.PlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/place_menu")
public class PlaceMenuController {

    @Autowired
    private IPlaceService placeService;


    @GetMapping("")
    public String showAll(ModelMap model) {
        model.addAttribute( "places", this.placeService.getPlaces() );
        return "place_menu";
    }

    @GetMapping("/getById")
    public String getById(@RequestParam(required = false) UUID idToFind, ModelMap model) {
        Place place = this.placeService.getPlaceById( idToFind );
        model.addAttribute( "place_found_by_id", place );
        return "place_menu";
    }

    @PostMapping("/addPlaces")
    public String addPlaces(@RequestParam(required = false) Integer numberOfPlaces, ModelMap model) {
        this.placeService.addPlaces( numberOfPlaces );
        model.addAttribute( "places", this.placeService.getPlaces() );
        return "place_menu";
    }


    @PostMapping("/setDate")
        public String setDate(@RequestParam(required = false)String id,
                              @RequestParam(required = false)String date,
                              ModelMap model) {

        this.placeService.setPlaceForDate( UUID.fromString( id ) , LocalDate.parse( date ) );
        model.addAttribute( "places", this.placeService.getPlaces() );
        return "place_menu";
    }
      @PostMapping("/delete")
    public String deletePlace(@RequestParam(required = false) String id, ModelMap model) {
        this.placeService.deletePlace( UUID.fromString( id )  );
        model.addAttribute( "places", this.placeService.getPlaces() );
        return "place_menu";
    }
}
