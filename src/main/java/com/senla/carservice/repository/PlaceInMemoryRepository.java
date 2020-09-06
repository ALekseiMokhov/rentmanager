package com.senla.carservice.repository;

import com.senla.carservice.domain.entities.garage.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Deprecated
public class PlaceInMemoryRepository implements IPlaceRepository {
    private final List <Place> places = new ArrayList <>();
    ;

    public PlaceInMemoryRepository() {

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
        } else {
            update( place );
        }

    }

    public void update(Place place) {
        this.places.set( this.places.indexOf( place ), place );
    }

    @Override
    public List <Place> findAll() {
        return this.places;
    }
}
