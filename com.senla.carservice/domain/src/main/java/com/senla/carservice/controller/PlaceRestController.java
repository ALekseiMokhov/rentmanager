package com.senla.carservice.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.carservice.dto.PlaceDto;
import com.senla.carservice.dto.mappers.PlaceMapper;
import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.service.interfaces.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return this.placeService.getPlaces().stream()
                .map(p -> PlaceMapper.INSTANCE.placeToDto(p))
                .collect(Collectors.toList());
    }

    @GetMapping("/all/{date}")
    public List<PlaceDto> getFreePlacesForDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return this.placeService.getFreePlacesForDate(date).stream()
                .map(p -> PlaceMapper.INSTANCE.placeToDto(p))
                .collect(Collectors.toList());
    }

    @PostMapping("/{quantity}")
    public void addPlaces(@PathVariable("quantity") int quantity) {
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

    @PutMapping("/{json}")
    public void savePlace(@PathVariable String json) {
        Gson gson = new GsonBuilder().create();
        PlaceDto placeDto = gson.fromJson(json, PlaceDto.class);
        this.placeService.savePlace(PlaceMapper.INSTANCE.dtoToPlace(placeDto));
    }

    @GetMapping("/free/{date}")
    public Place getFreePlace(@PathVariable @DateTimeFormat LocalDate date) {
        return this.placeService.getFreePlace(date);
    }

    @GetMapping("/{id}")
    public Place getPlaceById(@PathVariable UUID id) {
        return this.placeService.getPlaceById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePlace(@PathVariable UUID id) {
        this.placeService.deletePlace(id);
    }

}
