package main.service;

import main.entities.garage.Place;
import main.repository.PlaceRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlaceService {

    private final PlaceRepository repository = new PlaceRepository();

    public PlaceService() {

    }

    public List <Place> getPlaces() {
        return this.repository.findAll();
    }

    public List <Place> getFreePlacesForDate(LocalDate date) {
        return this.repository.findAll().stream().filter( p -> p.isFreeForBooking( date ) == true ).collect( Collectors.toList() );
    }


    public void savePlace(Place place) {
        this.repository.save( place );
        ;
    }

    public Place findFreePlace(LocalDate date) {
        for (Place place : this.repository.findAll()) {
            if (place.isFreeForBooking( date )) {
                return place;
            }

        }
        throw new NoSuchElementException( "There is no free places for this Date!" );
    }

    public Place findPlaceById(UUID id) {
        for (Place place : this.repository.findAll()) {
            if (place.getId().equals( id )) {
                return place;
            }
        }
        throw new NoSuchElementException( "There is no place with such id!" );
    }

    public void bookPlaceForDate(Place place, LocalDate date) {
        place.bookPlace( date );
        this.repository.save( place );
    }
}
