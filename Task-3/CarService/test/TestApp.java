import entities.Place;
import entities.Speciality;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.MasterService;
import service.OrderService;
import service.PlaceService;

import java.time.LocalDate;


public class TestApp {
    OrderService orderService;
    MasterService masterService;
    PlaceService placeService;

    @Before
    public void init() {
        placeService = new PlaceService();
        masterService = new MasterService();
        orderService = new OrderService();
    }

    @Test
    public void testMasterService() {
        masterService.addMaster( "Andrew", Speciality.MECHANIC );
        masterService.addMaster( "Ryan", Speciality.ELECTRICIAN );
        Assert.assertFalse( masterService.findByName( "Ryan" ) == null );
        masterService.removeMaster( 0 );
        Assert.assertTrue( masterService.findByName( "Andrew" ) == null );

    }

    @Test
    public void testPlaceService() {
        int idCounter = 0;
        placeService.addPlace( new Place() );
        placeService.addPlace( new Place() );
        placeService.addPlace( new Place() );
        placeService.addPlace( new Place() );
        for (Place place : placeService.getPlaces()) {
            if (place != null) {
                idCounter = place.getId();
            }
        }
        Assert.assertEquals( idCounter, 13 );
    }

    @Test
    public void testOrderService() {
        orderService.addOrder( masterService.addMaster( "Alex", Speciality.MECHANIC ), placeService.findFreePlace(), LocalDate.now() );
        orderService.addOrder( masterService.addMaster( "John", Speciality.MECHANIC ), placeService.findFreePlace(), LocalDate.of( 2020, 05, 30 ) );
        orderService.addOrder( masterService.addMaster( "Ivan", Speciality.MECHANIC ), placeService.findFreePlace(), LocalDate.of( 2020, 05, 31 ) );
        orderService.setNewMaster( orderService.findOrderById( 0 ), masterService.findByName( "John" ) );
        Assert.assertTrue( orderService.findOrderById( 0 ).getMaster().getFullName() == "John" );

        orderService.closeOrder( 0 );
        Assert.assertTrue( orderService.findOrderById( 0 ).isDone() );
        orderService.removeOrder( 0 );
        Assert.assertSame( orderService.getOrders()[ 0 ], null );

    }
}
