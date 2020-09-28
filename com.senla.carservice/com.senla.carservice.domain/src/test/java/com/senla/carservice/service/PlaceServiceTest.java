package com.senla.carservice.service;

import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.repository.jpa.PlaceJpaRepository;
import com.senla.carservice.util.calendar.Calendar;
import org.junit.jupiter.api.Assertions;
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
    private Place place = new Place( new Calendar() );
    private UUID id = place.getId();

    @Test()
    void getPlaces() {
        List <Place> list = new ArrayList <>();
        when( placeService.getPlaces() ).thenReturn( list );
        Assertions.assertEquals( list,placeService.getPlaces() );
    }

    @Test
    void addPlaces() {
        placeService.addPlaces( 22 );
        verify( mockRepo, times( 22 ) ).save( any( Place.class ) );
    }

    @Test
    void getFreePlacesForDate() {
        List <Place> list = new ArrayList <>();
        when( placeService.getFreePlacesForDate( LocalDate.now() ) ).thenReturn( list );
    }

    @Test
    void isPlaceSetForDate() {
        when( mockRepo.getById( id ) ).thenReturn( place );
        Boolean res = placeService.isPlaceSetForDate( id, LocalDate.now() );
        Assertions.assertEquals( false,res );
    }

    @Test
    void setPlaceForDate() {
        when( mockRepo.getById( id ) ).thenReturn( place );
        placeService.setPlaceForDate( id, LocalDate.now() );
        Assertions.assertEquals(true,
                placeService.getPlaceById( id ).getCalendar().isDateBooked( LocalDate.now() )  );

    }

    @Test
    void setPlaceId() {

    }

    @Test
    void setPlaceFree() {
    }

    @Test
    void addPlace() {
    }

    @Test
    void isPresent() {
    }

    @Test
    void savePlace() {
    }

    @Test
    void mergePlace() {
    }

    @Test
    void getFreePlace() {
    }

    @Test
    void getPlaceById() {
        this.placeService.getPlaceById( UUID.randomUUID() );
    }

    @Test
    void deletePlace() {
    }
}
