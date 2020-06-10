package service;

import entities.garage.Place;
import repository.PlaceRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlaceService implements IService{

    private final PlaceRepository repository;

    public PlaceService(PlaceRepository repository) {
        this.repository = repository;
    }

    public List <Place> getPlaces() {
        return this.repository.findAll();
    }

    public List <Place> getFreePlacesForDate(LocalDate date) {
        return this
                .repository
                .findAll()
                .stream()
                .filter( p -> p.getCalendar().isDateBooked( date ) == false )
                .collect( Collectors.toList() );
    }

    public boolean isPlaceSetForDate(Place place, LocalDate date) {
        return place.getCalendar()
                .isDateBooked( date );
    }


    public void setPlaceForDate(Place place, LocalDate date) {
        place.getCalendar().setDateForBooking( date );
        this.repository.save( place );
    }

    public void setPlaceFree(Place place, LocalDate date) {
        place.getCalendar().deleteBookedDate( date );
        this.repository.save( place );
    }


    public void savePlace(Place place) {
        this.repository.save( place );

    }

    public Place getFreePlace(LocalDate date) {
        for (Place place : this.repository.findAll()) {
            if (!place.getCalendar()
                    .isDateBooked( date )) {
                return place;
            }

        }
        throw new NoSuchElementException( "There is no free places for this Date!" );
    }

    public Place getPlaceById(UUID id) {
        return this.repository.findById( id );
    }


}
