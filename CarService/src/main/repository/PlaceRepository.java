package main.repository;

import main.entities.garage.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlaceRepository implements Repository <Place> {
    private List <Place> places = new ArrayList <>();

    @Override
    public Place findById(UUID id) {
        return this.places.stream().filter( master -> master.getId().equals( id ) ).findFirst().get();
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
