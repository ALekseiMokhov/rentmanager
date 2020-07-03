package com.senla.carservice.domain.repository;

import com.senla.carservice.domain.entities.garage.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class PlaceRepository implements IPlaceRepository {
    private final List <Place> places;

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
        throw new NoSuchElementException( "There is no place with provided id!" );
    }

    @Override
    public boolean isPresent(UUID id) {
        for (Place place : places) {
            if (place.getId().equals( id )) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void delete(UUID id) {
        this.places.removeIf( place -> place.getId().equals( id ) );
    }

    @Override
    public void save(Place place) {
        if (!this.places.contains( place )) {
            this.places.add( place );
            System.out.println( place.getCalendar() );
        } else {
            this.places.set( this.places.indexOf( place ), place );
        }

    }

    @Override
    public List <Place> findAll() {
        return this.places;
    }
}
