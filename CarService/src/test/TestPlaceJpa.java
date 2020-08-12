import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.repository.jpa.PlaceRepositoryJpa;
import dependency.injection.beanfactory.BeanFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.calendar.Calendar;

import java.util.List;
import java.util.UUID;

public class TestPlaceJpa {

    private final static Logger LOGGER = LoggerFactory.getLogger( TestPlaceJpa.class );
    PlaceRepositoryJpa repo;

    @BeforeEach
    public void init() throws Exception {
        BeanFactory.getInstance().loadMetadata( "com.senla.carservice" );
        BeanFactory.getInstance().instantiate( "com.senla.carservice" );
        BeanFactory.getInstance().injectDependencies();
        repo = (PlaceRepositoryJpa) BeanFactory.getInstance().getSingleton( "placerepositoryjpa" );
        for (int i = 0; i < 10; i++) {
            repo.save( new Place( new Calendar() ) );
        }

    }

    @Test
    public void testPlaceSave() {
        Place place = new Place();
        place.setCalendar( new Calendar() );
        LOGGER.info( "TEST PERSISTING PLACE " );
        repo.save( place );
        List <Place> places = repo.findAll();
        LOGGER.info( places.size() + " " + "OBJ WERE FOUND" );
        Assertions.assertEquals( places.size(), 11 );
    }

    @Test
    public void testPlaceDelete() {
        LOGGER.info( "TEST DELETING PLACE " );
        List <Place> places = repo.findAll();
        for (Place place1 : places) {
            repo.delete( place1.getId() );
        }
        Assertions.assertEquals( repo.findAll().size(), 0 );
    }

    @Test
    public void testPlaceUpdate() {
        LOGGER.info( "TEST UPDATING PLACE " );
        List <Place> places = repo.findAll();
        Place place = places.get( 0 );
        UUID idOld = place.getId();
        place.setId( UUID.randomUUID() );
        repo.save( place );
        Assertions.assertNotEquals( true, repo.isPresent( idOld ) );
    }
}
