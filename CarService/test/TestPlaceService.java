import main.entities.garage.Place;
import main.service.PlaceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;


public class TestPlaceService {
    private PlaceService service;

    @BeforeEach
    void init() {
        service = new PlaceService();
        for (int i = 0; i < 10; i++) {
            service.savePlace( new Place() );
        }
    }

    @Test
    void testWhetherAllNewPlacesAreFree() {
        Assertions.assertTrue( service.getFreePlacesForDate(LocalDate.now()).size() == 10 );

    }

    @Test
    void testFindPlaceById() {
       UUID id = service.getPlaces().get( 0 ).getId();
       Assertions.assertEquals( service.findPlaceById( id ),service.getPlaces().get( 0 ) );
    }


    @Test
    void testFindFreePlace() {
      Assertions.assertEquals( service.findFreePlace( LocalDate.now() ) , service.getPlaces().get( 0 ) );

    }

    @Test
    void testFIndFreePlaces() {
        for (int i = 0; i <5 ; i++) {
           service.getPlaces().get( i ).bookPlace( LocalDate.now() );
        }
        Assertions.assertTrue( service.getFreePlacesForDate( LocalDate.now() ).size()==5 );
    }
}
