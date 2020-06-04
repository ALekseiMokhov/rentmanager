import main.entities.master.*;
import main.service.MasterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.NoSuchElementException;

public class TestMasterService {
    private MasterService service;

    @BeforeEach
    void init() {
        service = new MasterService();
        service.saveMaster( new Reshaper( "Alex", 2.2 ) );
        service.saveMaster( new Reshaper( "Sergey", 3.4 ) );
        service.saveMaster( new Reshaper( "Ivan", 2.1 ) );
        service.saveMaster( new Mechanic( "Ivan", 4.0 ) );
        service.saveMaster( new Mechanic( "Anton", 2.3 ) );
        service.saveMaster( new Mechanic( "Vasiliy", 8.4 ) );
        service.saveMaster( new Electrician( "Vladimir", 2.3 ) );
        service.saveMaster( new Painter( "Grigory", 1.2 ) );
        service.saveMaster( new Painter( "Maxim", 2.3 ) );
    }

    @Test
    void testFindByNameAndSpeciality() {
        Assertions.assertNotEquals( service.findByNameAndSpeciality( "Ivan", Mechanic.class ),
                service.findByNameAndSpeciality( "Ivan", Reshaper.class ) );
        System.out.println( service.findByNameAndSpeciality( "Ivan", Mechanic.class ) );
    }

    @Test
    void testAddMaster() {
        service.saveMaster( new Reshaper( "Pavel", 3.4 ) );
        Assertions.assertNotNull( service.findByNameAndSpeciality( "Pavel", Reshaper.class ) );
    }

    @Test
    void testRemoveMaster() {

        service.removeMaster( service.findByNameAndSpeciality( "Alex", Reshaper.class ).getId() );

        Assertions.assertThrows( NoSuchElementException.class
                , () -> service.findByNameAndSpeciality( "Alex", Reshaper.class ) );
    }

    @Test
    void testMastersByAlphabet() {
        for (Master master : service.getMastersByAlphabet()) {
            System.out.println( master.getFullName() );
        }
        Assertions.assertEquals( service.getMastersByAlphabet().get( 1 ), service.findByNameAndSpeciality( "Anton", Mechanic.class ) );
        Assertions.assertEquals( service.getMastersByAlphabet().get( 8 ), service.findByNameAndSpeciality( "Vladimir", Electrician.class ) );
    }

    @Test
    void tetstGetFreeMastersForDate() {
        Master master = service.findByNameAndSpeciality( "Ivan", Mechanic.class );
        master.bookMaster( LocalDate.now() );
        Assertions.assertTrue( service.getFreeMasters( LocalDate.now() ).size() == 8 );

    }

    @Test
    void testReturnMastersBySpeciality() {
        Assertions.assertEquals( service.getMastersBySpeciality( Reshaper.class ).size(), 3 );
    }
}
