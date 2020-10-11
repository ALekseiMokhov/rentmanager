package com.senla.carservice.controller;


import com.senla.carservice.dto.PlaceDto;
import com.senla.carservice.service.interfaces.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/places")
/*@Profile({"rest","test"})*/
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

    @PostMapping("/{quanity}")
    public void addPlaces(@PathVariable int quantity) {
        this.placeService.addPlaces(quantity);
    }

    @PostMapping("/")
    public void addPlace() {
        this.placeService.addPlace();

    }

    @GetMapping("/is-set/{id}/{date}")
    public boolean isPlaceSetForDate(@PathVariable UUID id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.placeService.isPlaceSetForDate(id, date);
    }

    @PatchMapping("/book/{id}/{date}")
    public void setPlaceForDate(@PathVariable UUID id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        this.placeService.setPlaceForDate(id, date);
    }

    @PatchMapping("/un-book/{id}/{date}")
    public void setPlaceFree(@PathVariable UUID id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        this.placeService.setPlaceFree(id, date);
    }

    @PutMapping("/")
    public void savePlace(@RequestParam PlaceDto dto) {
        this.placeService.savePlace(dto);
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
    public void deletePlace(@PathVariable UUID id) {
        this.placeService.deletePlace(id);
    }

}
