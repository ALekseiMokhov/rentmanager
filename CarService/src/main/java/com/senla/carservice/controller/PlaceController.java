package com.senla.carservice.controller;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.service.IPlaceService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PlaceController {
    private IPlaceService placeService;

    public PlaceController(IPlaceService placeService) {
        this.placeService = placeService;
    }

    public List <Place> getPlaces() {
        return this.placeService.getPlaces();
    }

    public List <Place> getFreePlacesForDate(LocalDate date) {
        return this.placeService.getFreePlacesForDate( date );
    }


    public boolean isPlaceSetForDate(Place place, LocalDate date) {
        return this.placeService.isPlaceSetForDate( place, date );
    }

    public void setPlaceForDate(Place place, LocalDate date) {
        this.placeService.setPlaceForDate( place, date );
    }

    public void setPlaceId(Place place, UUID id) {
        this.placeService.setPlaceId( place,id );
    }

    public void setPlaceFree(Place place, LocalDate date) {
        this.placeService.setPlaceFree( place, date );
    }

    public void savePlace(Place place) {
        this.placeService.savePlace( place );
    }

    public Place getFreePlace(LocalDate date) {
        return this.placeService.getFreePlace( date );
    }

    public Place getPlaceById(UUID id) {
        return this.placeService.getPlaceById( id );
    }


}
