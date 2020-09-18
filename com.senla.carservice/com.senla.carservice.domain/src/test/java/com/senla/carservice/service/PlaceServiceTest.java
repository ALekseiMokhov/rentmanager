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
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {
    private IGenericRepository repository = Mockito.mock( PlaceJpaRepository.class);
    @InjectMocks
    private PlaceService placeService;

    @Test
    void testRepoMock() {
        assertNotNull( repository );
        assertTrue( repository instanceof PlaceJpaRepository );
    }

    @Test()
    void getPlaces() {
        List <Place> list = new ArrayList <>();
        when( placeService.getPlaces() ).thenReturn( list );
    }

    @Test
    void addPlaces() {
        placeService.addPlaces( 22 );
        verify( repository,times( 22 ) ).save( any(Place.class) );
    }

    @Test
    void getFreePlacesForDate() {
        List<Place>list = new ArrayList <>();
        when(placeService.getFreePlacesForDate( LocalDate.now() )).thenReturn(list) ;
    }

    @Test
    void isPlaceSetForDate() {


        
    }

    @Test
    void setPlaceForDate() {
        
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
        this.placeService.getPlaceById( UUID.randomUUID() ) ;
    }

    @Test
    void loadFromCsv() {
    }

    @Test
    void exportToCsv() {
    }

    @Test
    void loadPlacesFromJson() {
    }

    @Test
    void exportPlacesToJson() {
    }

    @Test
    void deletePlace() {
    }
}
