package com.senla.carservice.controller.template;

import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.service.interfaces.IPlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/place_menu")
@Profile("ui")
public class PlaceMenuController {

    @Autowired
    private IPlaceService placeService;


    @GetMapping("")
    public String showAll(ModelMap model) {
        model.addAttribute( "places", this.placeService.getPlaces() );
        return "place_menu";
    }

    @GetMapping("/getById")
    public String getById(@RequestParam(required = false) UUID id, ModelMap model) {
        Place place = this.placeService.getPlaceById( id );
        model.addAttribute( "place_found_by_id", place );
        model.addAttribute( "message", "Place was found! " );

        return "place_menu";
    }
    @GetMapping("/getFreePlace")
    public String getFreePlace(@RequestParam(required = false) String date, ModelMap model) {
        Place place = this.placeService.getFreePlace( LocalDate.parse( date ) );
        model.addAttribute( "free_place", place );
        model.addAttribute( "message", "Place was found! ");

        return "place_menu";
    }
   

    @PostMapping("/addPlaces")
    public String addPlaces(@RequestParam(required = false) Integer numberOfPlaces, ModelMap model) {
        this.placeService.addPlaces( numberOfPlaces );
        model.addAttribute( "places", this.placeService.getPlaces() );
        model.addAttribute( "message", "Places were added: " + numberOfPlaces );

        return "place_menu";
    }


    @PostMapping("/setDate")
    public String setDate(@RequestParam(required = false) UUID id,
                          @RequestParam(required = false) String date,
                          ModelMap model) {

        this.placeService.setPlaceForDate( id, LocalDate.parse( date ) );
        model.addAttribute( "message", "Place with id " + id + " was set for " + date );
        return "place_menu";
    }

    @PostMapping("/unbookPlace")
    public String setId(@RequestParam(required = false) UUID id,
                          @RequestParam(required = false) String date,
                          ModelMap model) {

        this.placeService.setPlaceFree( id, LocalDate.parse( date ) );
        model.addAttribute( "message", "Place is free for the date: " + date);
        return "place_menu";
    }
    @PostMapping("/delete")
    public String deletePlace(@RequestParam(required = false) UUID id, ModelMap model) {
        this.placeService.deletePlace(  id  );
        model.addAttribute( "places", this.placeService.getPlaces() );
        model.addAttribute( "message", "Place with id " + id + " was deleted!" );
        return "place_menu";
    }


}
