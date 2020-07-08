package com.senla.carservice.controller;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.service.IPlaceService;
import dependency.injection.annotations.Autowired;
import dependency.injection.annotations.components.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class PlaceController {
    @Autowired
    private IPlaceService placeService;

    public PlaceController() {

    }

    public List <Place> getPlaces() {
        return this.placeService.getPlaces();
    }

    public List <Place> getFreePlacesForDate(LocalDate date) {
        return this.placeService.getFreePlacesForDate( date );
    }

    public void addPlaces(int i) {
        this.placeService.addPlaces( i );
    }

    public UUID addPlace() {
        return this.placeService.addPlace();
    }


    public boolean isPlaceSetForDate(UUID id, LocalDate date) {
        return this.placeService.isPlaceSetForDate( id, date );
    }

    public void setPlaceForDate(UUID id, LocalDate date) {
        this.placeService.setPlaceForDate( id, date );
    }

    public void setPlaceId(UUID current, UUID newId) {
        this.placeService.setPlaceId( current, newId );
    }

    public void setPlaceFree(UUID id, LocalDate date) {
        this.placeService.setPlaceFree( id, date );
    }

    public void savePlace(UUID id) {
        this.placeService.savePlace( id );
    }

    public Place getFreePlace(LocalDate date) {
        return this.placeService.getFreePlace( date );
    }

    public Place getPlaceById(UUID id) {
        return this.placeService.getPlaceById( id );
    }

    public void loadPlacesFromCsv() {
        this.placeService.loadFromCsv();
    }

    public void exportPlacesToCsv() {
        this.placeService.exportToCsv();
    }

    public void loadFromJson() {
        this.placeService.loadPlacesFromJson();
    }

    public void exportToJson() {
        this.placeService.exportPlacesToJson();
    }

}
