import main.entities.master.*;
import main.repository.MasterRepository;
import main.service.MasterService;
import main.util.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;

public class TestMasterService {
    private MasterService service;
    private MasterRepository repository;

    @BeforeEach
    void init() {
        repository = new MasterRepository();
        service = new MasterService( repository );
        service.saveMaster( new Reshaper( "Alex", 2.2, new Calendar(), Speciality.RESHAPER ) );
        service.saveMaster( new Reshaper( "Sergey", 3.4, new Calendar(), Speciality.RESHAPER ) );
        service.saveMaster( new Reshaper( "Ivan", 2.1, new Calendar(), Speciality.RESHAPER ) );
        service.saveMaster( new Mechanic( "Ivan", 4.0, new Calendar(), Speciality.MECHANIC ) );
        service.saveMaster( new Mechanic( "Anton", 2.3, new Calendar(), Speciality.MECHANIC ) );
        service.saveMaster( new Mechanic( "Vasiliy", 8.4, new Calendar(), Speciality.MECHANIC ) );
        service.saveMaster( new Electrician( "Vladimir", 2.3, new Calendar(), Speciality.ELECTRICIAN ) );
        service.saveMaster( new Painter( "Grigory", 1.2, new Calendar(), Speciality.PAINTER ) );
        service.saveMaster( new Painter( "Maxim", 2.3, new Calendar(), Speciality.PAINTER ) );
    }

    @Test
    void testFindByNameAndSpeciality() {
        Assertions.assertNotEquals( this.service.getByNameAndSpeciality( "Ivan", Speciality.MECHANIC ),
                this.service.getByNameAndSpeciality( "Ivan", Speciality.RESHAPER ) );
        System.out.println( this.service.getByNameAndSpeciality( "Ivan", Speciality.MECHANIC) );
    }

    @Test
    void testAddMaster() {
        this.service.saveMaster( new Reshaper( "Pavel", 3.4, new Calendar(), Speciality.RESHAPER ) );
        Assertions.assertNotNull( this.service.getByNameAndSpeciality( "Pavel", Speciality.RESHAPER ) );
    }

    @Test
    void testRemoveMaster() {

        this.service.removeMaster( service.getByNameAndSpeciality( "Alex", Speciality.RESHAPER ).getId() );

        Assertions.assertThrows( NoSuchElementException.class
                , () -> service.getByNameAndSpeciality( "Alex", Speciality.RESHAPER ) );
    }

    @Test
    void testMastersByAlphabet() {
        for (Master master : service.getMastersByAlphabet()) {
            System.out.println( master.getFullName() );
        }
        Assertions.assertEquals( service.getMastersByAlphabet().get( 1 ), service.getByNameAndSpeciality( "Anton", Speciality.MECHANIC ) );
        Assertions.assertEquals( service.getMastersByAlphabet().get( 8 ), service.getByNameAndSpeciality( "Vladimir", Speciality.ELECTRICIAN ) );
    }

    @Test
    void tetstGetFreeMastersForDate() {
        Master master = service.getByNameAndSpeciality( "Ivan", Speciality.MECHANIC );
        master.getCalendar().setDateForBooking( LocalDate.now() );
        Assertions.assertTrue( service.getFreeMasters( LocalDate.now() ).size() == 8);

    }

    @Test
    void testReturnMastersBySpeciality() {
        Assertions.assertEquals( service.getMastersBySpeciality( Speciality.RESHAPER).size(), 3 );
    }
}
