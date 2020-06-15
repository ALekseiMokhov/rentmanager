package com.senla.carservice.domain.repository;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.service.IPlaceService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class PlaceRepository implements IPlaceRepository  {
    private List <Place> places;

    public PlaceRepository() {
        this.places = new ArrayList <>();
    }

    @Override
    public Place findById(UUID id) {
        for (Place place : places) {
            if (place.getId().equals( id )) {
                return place;
            }
        }
        throw new NoSuchElementException( "There is no place with such id!" );
    }

    @Override
    public List <Place> findAll() {
        return this.places;
    }

    @Override
    public void delete(UUID id) {
        this.places.removeIf( place -> place.getId().equals( id ) );
    }

    @Override
    public void save(Place place) {
        if (!this.places.contains( place )) {
            this.places.add( place );
        } else {
            this.places.set( this.places.indexOf( place ), place );
        }
    }


}
