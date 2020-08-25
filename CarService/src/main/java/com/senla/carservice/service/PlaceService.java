package com.senla.carservice.service;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.repository.IPlaceRepository;
import dependency.injection.annotations.Autowired;
import util.calendar.Calendar;
import util.csv.CsvPlaceParser;
import util.csv.CsvPlaceWriter;
import util.serialisation.GsonPlaceParser;
import util.serialisation.GsonPlaceWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;


public class PlaceService implements IPlaceService {
    @Autowired
    private IPlaceRepository repository;

    public PlaceService() {

    }

    public IPlaceRepository getRepository() {
        return repository;
    }

    public List <Place> getPlaces() {
        return this.repository.findAll();
    }

    public void addPlaces(int i) {
        for (int j = 0; j < i; j++) {
            this.repository.save( new Place( new Calendar() ) );


        }
    }

    public List <Place> getFreePlacesForDate(LocalDate date) {
        return this
                .repository
                .findAll()
                .stream()
                .filter( p -> p.getCalendar().isDateBooked( date ) == false )
                .collect( Collectors.toList() );
    }

    public boolean isPlaceSetForDate(UUID id, LocalDate date) {
        Place place = this.repository.findById( id );
        return place.getCalendar()
                .isDateBooked( date );
    }


    public void setPlaceForDate(UUID id, LocalDate date) {
        Place place = this.repository.findById( id );
        if(place.getCalendar()==null){
            place.setCalendar( new Calendar() );
        }
        place.getCalendar().setDateForBooking( date );
        this.repository.save( place );
    }

    public void setPlaceId(UUID id, UUID newId) {
        Place place = this.repository.findById( id );
        place.setId( newId );
        this.repository.save( place );
        this.repository.delete( id );
    }

    public void setPlaceFree(UUID id, LocalDate date) {
        Place place = this.repository.findById( id );
        place.getCalendar().deleteBookedDate( date );
        this.repository.save( place );
    }

    public UUID addPlace() {
        Place place = new Place( new Calendar() );
        this.repository.save( place );
        return place.getId();
    }


    public boolean isPresent(UUID id) {
        return this.repository.findById( id ) != null;
    }

    public void savePlace(UUID id) {
        if (!this.isPresent( id )) {
            Place place = new Place( new Calendar() );
            place.setId( id );
            this.repository.save( place );
        } else this.repository.save( this.repository.findById( id ) );

    }


    public void mergePlace(Place place) {
        this.repository.save( place );
    }

    public Place getFreePlace(LocalDate date) {
        for (Place place : this.repository.findAll()) {
            if (!place.getCalendar()
                    .isDateBooked( date )) {
                return place;
            }

        }
        throw new NoSuchElementException( "There are no free places for this Date!" );
    }

    public Place getPlaceById(UUID id) {
        try {
            return this.repository.findById( id );
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        throw new NoSuchElementException( "There is no place with provided id!" );
    }

    @Override
    public void loadFromCsv() {
        try {
            List <Place> list = CsvPlaceParser.load();
            for (Place place : list) {
                mergePlace( place );
            }
            System.out.println( list.size() + " places were loaded from file!" );
        } catch (IOException e) {
            System.err.println( "Check the path to file!" );
        }

    }

    @Override
    public void exportToCsv() {

        try {
            CsvPlaceWriter.writePlaces( this.repository.findAll() );

            System.out.println( this.repository.findAll().size() + " places were successfully written to csv file!" );

        } catch (IOException e) {
            System.err.println( "There is a problem with export!Check path to the file!" );
        }

    }

    @Override
    public void loadPlacesFromJson() {
        try {
            List <Place> list = GsonPlaceParser.load();
            for (Place place : list) {
                mergePlace( place );
            }

        } catch (IOException e) {
            System.err.println( "There is a problem with import!Check path to the .json file!" );
        }
    }

    @Override
    public void exportPlacesToJson() {
        try {
            GsonPlaceWriter.serializePlaces( this.repository.findAll() );
        } catch (IOException e) {
            System.err.println( "There is a problem with export!Check path to the file!" );
        }
    }


}
