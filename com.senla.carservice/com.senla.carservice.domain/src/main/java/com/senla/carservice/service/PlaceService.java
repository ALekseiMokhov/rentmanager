package com.senla.carservice.service;


import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.service.interfaces.IPlaceService;
import com.senla.carservice.util.calendar.Calendar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class PlaceService implements IPlaceService {
    @Autowired
    @Qualifier("placeJpaRepository")
    private IGenericRepository <Place> repository;


    public PlaceService() {

    }

    public List <Place> getPlaces() {
        return this.repository.findAll();
    }


    public void addPlaces(int i) {
        for (int j = 0; j < i; j++) {
            this.repository.save( new Place( new Calendar() ) );
        }
    }

    @Transactional
    public List <Place> getFreePlacesForDate(LocalDate date) {

        return this.repository.findAll().stream()
                .filter( p -> p.getCalendar().isDateBooked( date ) == false )
                .collect( Collectors.toList() );
    }

    @Transactional
    public boolean isPlaceSetForDate(UUID id, LocalDate date) {
        Place place = this.repository.getById( id );
        return place.getCalendar()
                .isDateBooked( date );
    }

    @Transactional
    public void setPlaceForDate(UUID id, LocalDate date) {
        Place place = this.repository.getById( id );
        if (place.getCalendar() == null) {
            place.setCalendar( new Calendar() );
        }
        place.getCalendar().setDateForBooking( date );

    }

    @Transactional
    public void setPlaceId(UUID id, UUID newId) {
        Place place = this.repository.getById( id );
        place.setId( newId );

    }

    @Transactional
    public void setPlaceFree(UUID id, LocalDate date) {
        Place place = this.repository.getById( id );
        place.getCalendar().deleteBookedDate( date );

    }

    @Transactional
    public UUID addPlace() {
        Place place = new Place( new Calendar() );
        this.repository.save( place );

        return place.getId();
    }


    public boolean isPresent(UUID id) {
        return this.repository.getById( id ) != null;
    }


    @Transactional
    public Place getFreePlace(LocalDate date) {
        List <Place> res = this.repository.findAll();
        for (Place place : res) {
            if (!place.getCalendar()
                    .isDateBooked( date )) {
                return place;
            }

        }
        throw new NoSuchElementException( "There is no free place for chosen date!" );
    }

    public Place getPlaceById(UUID id) {
        return this.repository.getById( id );

    }


    @Override
    public void deletePlace(UUID id) {
        this.repository.delete( id );
    }

    @Override
    public void savePlace(Place place) {
        this.repository.save( place );
    }
}
