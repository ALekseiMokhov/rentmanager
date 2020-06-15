import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.repository.PlaceRepository;
import com.senla.carservice.domain.service.PlaceService;
import util.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;


public class TestPlaceService {
    private PlaceService service;
    private PlaceRepository repository;

    @BeforeEach
    void init() {
        this.repository = new PlaceRepository();
        this.service = new PlaceService( repository );
        for (int i = 0; i < 10; i++) {
            this.service.savePlace( new Place( new Calendar() ) );
        }
    }

    @Test
    void testWhetherAllNewPlacesAreFree() {
        Assertions.assertTrue( this.service.getFreePlacesForDate( LocalDate.now() ).size() == 10 );

    }

    @Test
    void testFindPlaceById() {
        UUID id = this.service.getPlaces().get( 0 ).getId();
        Assertions.assertEquals( service.getPlaceById( id ), this.service.getPlaces().get( 0 ) );
    }


    @Test
    void testFindFreePlace() {
        Assertions.assertEquals( this.service.getFreePlace( LocalDate.now() ), this.service.getPlaces().get( 0 ) );

    }

    @Test
    void testFIndFreePlaces() {
        for (int i = 0; i < 5; i++) {
            this.service.getPlaces().get( i ).getCalendar().setDateForBooking( LocalDate.now() );
        }
        Assertions.assertTrue( this.service.getFreePlacesForDate( LocalDate.now() ).size() == 5 );
    }
}
