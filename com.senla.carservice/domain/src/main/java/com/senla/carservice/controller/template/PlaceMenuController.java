package com.senla.carservice.controller.template;

import com.senla.carservice.dto.PlaceDto;
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
@RequestMapping("/places")
@Profile("ui")
public class PlaceMenuController {

    @Autowired
    private IPlaceService placeService;


    @GetMapping("")
    public String showAll(ModelMap model) {
        model.addAttribute( "places", this.placeService.getPlaceDto() );
        return "places";
    }

    @GetMapping("/getById")
    public String getById(@RequestParam(required = false) UUID id, ModelMap model) {
        PlaceDto place = this.placeService.getPlaceById( id );
        model.addAttribute( "place_found_by_id", place );
        model.addAttribute( "message", "Place was found! " );

        return "places";
    }
    @GetMapping("/free")
    public String getFreePlace(@RequestParam(required = false) String date, ModelMap model) {
        PlaceDto place = this.placeService.getFreePlaceDto( LocalDate.parse( date ) );
        model.addAttribute( "free_place", place );
        model.addAttribute( "message", "Place was found! ");

        return "places";
    }
   

    @PostMapping("/add")
    public String addPlaces(@RequestParam(required = false, defaultValue = "1") Integer numberOfPlaces, ModelMap model) {
        this.placeService.addPlaces( numberOfPlaces );
        model.addAttribute( "places", this.placeService.getPlaceDto() );
        model.addAttribute( "message", "Places were added: " + numberOfPlaces );

        return "places";
    }


    @PostMapping("/set-date")
    public String setDate(@RequestParam(required = false) UUID id,
                          @RequestParam(required = false) String date,
                          ModelMap model) {

        this.placeService.setPlaceForDate( id, LocalDate.parse( date ) );
        model.addAttribute( "message", "Place with id " + id + " was set for " + date );
        return "places";
    }

    @PostMapping("/un-book")
    public String setId(@RequestParam(required = false) UUID id,
                          @RequestParam(required = false) String date,
                          ModelMap model) {

        this.placeService.setPlaceFree( id, LocalDate.parse( date ) );
        model.addAttribute( "message", "Place is free for the date: " + date);
        return "places";
    }
    @PostMapping("/delete")
    public String deletePlace(@RequestParam(required = false) UUID id, ModelMap model) {
        this.placeService.deletePlace(  id  );
        model.addAttribute( "places", this.placeService.getPlaceDto() );
        model.addAttribute( "message", "Place with id " + id + " was deleted!" );
        return "places";
    }


}
