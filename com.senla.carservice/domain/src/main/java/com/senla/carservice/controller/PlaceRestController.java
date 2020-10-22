package com.senla.carservice.controller;


import com.senla.carservice.dto.PlaceDto;
import com.senla.carservice.service.interfaces.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/places")
@Profile({"rest", "test"})
public class PlaceRestController {
    @Autowired
    @Qualifier("placeService")
    private IPlaceService placeService;

    public PlaceRestController() {

    }

    @GetMapping("/")
    public List<PlaceDto> getPlaces() {
        return this.placeService.getPlaceDto();
    }

    @GetMapping("/free")
    public List<PlaceDto> getFreePlacesForDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.placeService.getFreePlaceDtoForDate(date);
    }

    @PostMapping("/{quantity}")
    public ResponseEntity addPlaces(@PathVariable Integer quantity) {
        this.placeService.addPlaces(quantity);
        return new ResponseEntity("Places added to Garage: "+quantity, HttpStatus.CREATED );
    }

    @PostMapping("/")
    public ResponseEntity addPlace() {
        this.placeService.addPlace();
        return new ResponseEntity("Place was added to Garage", HttpStatus.CREATED );
    }

    @GetMapping("/is-set/{id}/{date}")
    public ResponseEntity isPlaceSetForDate(@PathVariable UUID id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return new ResponseEntity ("Is set for " + date +" :" + this.placeService.isPlaceSetForDate(id, date),HttpStatus.FOUND);}

    @PatchMapping("/book/{id}/{date}")
    public ResponseEntity setPlaceForDate(@PathVariable UUID id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        this.placeService.setPlaceForDate(id, date);
       return new ResponseEntity("Place with id "+id+" was set for "+date, HttpStatus.ACCEPTED) ;
    }

    @PatchMapping("/un-book/{id}/{date}")
    public ResponseEntity setPlaceFree(@PathVariable UUID id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        this.placeService.setPlaceFree(id, date) ;
       return new ResponseEntity( "Place with id "+id+" was set for "+date, HttpStatus.ACCEPTED ) ;
    }

    @PutMapping("/")
    public ResponseEntity savePlace(@RequestBody PlaceDto dto) {
        this.placeService.savePlace(dto);
        return new ResponseEntity( "Place was saved ", HttpStatus.ACCEPTED ) ;

    }

    @GetMapping("/free/{date}")
    public PlaceDto getFreePlace(@PathVariable @DateTimeFormat LocalDate date) {
        return this.placeService.getFreePlaceDto(date);
    }

    @GetMapping("/{id}")
    public PlaceDto getPlaceById(@PathVariable UUID id) {
        return this.placeService.getPlaceById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePlace(@PathVariable UUID id) {
        this.placeService.deletePlace(id);
        return new ResponseEntity( "Place with id "+id+" was deleted ", HttpStatus.ACCEPTED ) ;
    }


}
