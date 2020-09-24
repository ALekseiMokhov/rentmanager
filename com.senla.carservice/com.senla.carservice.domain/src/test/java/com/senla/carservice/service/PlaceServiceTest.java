package com.senla.carservice.service;

import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.repository.jpa.PlaceJpaRepository;
import com.senla.carservice.util.calendar.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {
    private IGenericRepository mockRepo
            = Mockito.mock( PlaceJpaRepository.class );
    @InjectMocks
    private PlaceService placeService;
    private Place place;
    private UUID id;

    @BeforeEach
    public void init() {
        place = new Place( new Calendar() );
        id = place.getId();
        when( mockRepo.getById( id ) ).thenReturn( place );
    }

    @Test()
    void getPlaces() {
        List <Place> list = new ArrayList <>();
        when( this.placeService.getPlaces() ).thenReturn( list );
        Assertions.assertEquals( list, placeService.getPlaces() );
    }

    @Test
    void addPlaces() {
        placeService.addPlaces( 22 );
        verify( mockRepo, times( 22 ) ).save( any( Place.class ) );
    }

    @Test
    void getFreePlacesForDate() {
        List <Place> list = new ArrayList <>();
        when( this.placeService.getFreePlacesForDate( LocalDate.now() ) ).thenReturn( list );
    }

    @Test
    void isPlaceSetForDate() {

        Boolean res = this.placeService.isPlaceSetForDate( id, LocalDate.now() );
        Assertions.assertEquals( false, res );
    }

    @Test
    void setPlaceForDate() {

        this.placeService.setPlaceForDate( id, LocalDate.now() );
        Assertions.assertEquals( true,
                placeService.getPlaceById( id ).getCalendar().isDateBooked( LocalDate.now() ) );

    }

    @Test
    void setPlaceId() {
        UUID newId = UUID.randomUUID();
        this.placeService.setPlaceId( id, newId );
        verify( mockRepo, times( 1 ) ).getById( id );
    }

    @Test
    void setPlaceFree() {
        LocalDate date = LocalDate.now();
        this.placeService.setPlaceForDate( id, date );
        Assertions.assertEquals( true,
                placeService.getPlaceById( id ).getCalendar().isDateBooked( LocalDate.now() ) );
        this.placeService.setPlaceFree( id, date );
        Assertions.assertEquals( false,
                placeService.getPlaceById( id ).getCalendar().isDateBooked( LocalDate.now() ) );
    }

    @Test
    void addPlace() {
        this.placeService.addPlace();
        verify( mockRepo, times( 1 ) ).save( any( Place.class ) );
    }

    @Test
    void isPresent() {
        this.placeService.isPresent( id );
        verify( mockRepo, times( 1 ) ).getById( id );
    }

    @Test
    void savePlace() {
        this.placeService.savePlace( place );
        verify( mockRepo, times( 1 ) ).save( place );
    }


    @Test
    void getFreePlace() {
        this.placeService.setPlaceForDate( id, LocalDate.now() );
        Assertions.assertThrows( RuntimeException.class, () -> this.placeService.getFreePlace( LocalDate.now() ) );

    }


    @Test
    void deletePlace() {
        this.placeService.deletePlace( id );
        verify( mockRepo, times( 1 ) ).delete( id );
    }
}
