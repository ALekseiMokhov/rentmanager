package com.senla.carservice.service;


import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.repository.IGenericRepository;
import com.senla.carservice.util.calendar.Calendar;
import com.senla.carservice.util.csv.CsvPlaceParser;
import com.senla.carservice.util.csv.CsvPlaceWriter;
import com.senla.carservice.util.serialisation.GsonPlaceParser;
import com.senla.carservice.util.serialisation.GsonPlaceWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class PlaceService implements IPlaceService {

    private IGenericRepository <Place> repository;


    public PlaceService() {

    }

    @Autowired
    @Qualifier("genericJpaRepository")
    public void setRepository(IGenericRepository <Place> repository) {
        this.repository = repository;
        this.repository.setClass( Place.class );
    }

    public List <Place> getPlaces() {
        return this.repository.findAll();
    }

    public void addPlaces(int i) {
        for (int j = 0; j < i; j++) {
            try {
                this.repository.save( new Place( new Calendar() ) );
            } catch (Exception e) {
                log.error( "Failed to add places! " + e );
            }


        }
    }

    public List <Place> getFreePlacesForDate(LocalDate date) {
        List <Place> res = new ArrayList <>();
        try {
            res = this.repository.findAll();
        } catch (Exception e) {
            log.error( "failed to find all places! " + e );
            throw new RuntimeException();
        }
        return res.stream()
                .filter( p -> p.getCalendar().isDateBooked( date ) == false )
                .collect( Collectors.toList() );
    }

    public boolean isPlaceSetForDate(UUID id, LocalDate date) {
        Place place = null;
        try {
            place = this.repository.getById( id );
        } catch (Exception e) {
            log.error( "failed to find place by id! " + e );
            throw new RuntimeException();

        }
        return place.getCalendar()
                .isDateBooked( date );
    }


    public void setPlaceForDate(UUID id, LocalDate date) {
        Place place = this.repository.getById( id );
        if (place.getCalendar() == null) {
            place.setCalendar( new Calendar() );
        }
        place.getCalendar().setDateForBooking( date );
        try {
            this.repository.save( place );
        } catch (Exception e) {
            log.error( "failed to save place! " + e );
            throw new RuntimeException();
        }
    }

    public void setPlaceId(UUID id, UUID newId) {
        Place place = this.repository.getById( id );
        place.setId( newId );
        try {
            this.repository.save( place );
            this.repository.delete( id );
        } catch (Exception e) {
            log.error( "failed to update place! " + e );
            throw new RuntimeException();
        }
    }

    public void setPlaceFree(UUID id, LocalDate date) {
        Place place = this.repository.getById( id );
        place.getCalendar().deleteBookedDate( date );
        try {
            this.repository.save( place );
        } catch (Exception e) {
            log.error( "failed to update all place! " + e );
            throw new RuntimeException();
        }
    }

    public UUID addPlace() {
        Place place = new Place( new Calendar() );
        try {
            this.repository.save( place );
        } catch (Exception e) {
            log.error( "failed to add place! " + e );
            throw new RuntimeException();
        }
        return place.getId();
    }


    public boolean isPresent(UUID id) {
        return this.repository.getById( id ) != null;
    }

    public void savePlace(UUID id) {
        try {
            if (!this.isPresent( id )) {
                Place place = new Place( new Calendar() );
                place.setId( id );
                this.repository.save( place );
            } else this.repository.save( this.repository.getById( id ) );
        } catch (Exception e) {
            log.error( "failed to save place! " + e );
            throw new RuntimeException();
        }

    }


    public void mergePlace(Place place) {
        try {
            this.repository.save( place );
        } catch (Exception e) {
            log.error( "failed to merge place! " + e );
            throw new RuntimeException();
        }
    }

    public Place getFreePlace(LocalDate date) {
        List <Place> res = null;
        try {
            res = this.repository.findAll();
        } catch (Exception e) {
            log.error( "failed to find places! " + e );
            throw new RuntimeException();
        }
        for (Place place : res) {
            if (!place.getCalendar()
                    .isDateBooked( date )) {
                return place;
            }

        }
        throw new NoSuchElementException( "There are no free places for this Date!" );          /*unreachable*/
    }

    public Place getPlaceById(UUID id) {
        try {
            return this.repository.getById( id );
        } catch (Exception e) {
            System.out.println(e);
            log.error( "failed to get place! " + e );
            throw new RuntimeException();
        }
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
            log.error( "failed to load places from csv! " + e );
            throw new RuntimeException();
        }

    }

    @Override
    public void exportToCsv() {

        try {
            CsvPlaceWriter.writePlaces( this.repository.findAll() );

            System.out.println( this.repository.findAll().size() + " places were successfully written to csv file!" );

        } catch (IOException e) {
            log.error( "There is a problem with export!Check path to the file! " + e );
            throw new RuntimeException();
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
            log.error( "There is a problem with import!Check path to the file! " + e );
            throw new RuntimeException();
        }
    }

    @Override
    public void exportPlacesToJson() {
        try {
            GsonPlaceWriter.serializePlaces( this.repository.findAll() );
        } catch (IOException e) {
            log.error( "There is a problem with export!Check path to the file! " + e );
            throw new RuntimeException();
        }
    }

    @Override
    public void deletePlace(UUID id) {
        this.repository.delete( id );
    }
}
