package com.senla.carservice.service;

import com.senla.carservice.entity.master.*;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.repository.jpa.MasterJpaRepository;
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
class MasterServiceTest {
    private IGenericRepository mockRepo
            = Mockito.mock( MasterJpaRepository.class );
    @InjectMocks
    private MasterService masterService;
    private AbstractMaster testMechanic;
    private AbstractMaster secondReshaper;
    private AbstractMaster testReshaper;
    private AbstractMaster testElectrician;
    private AbstractMaster testPainter;
    private List <AbstractMaster> masterList = new ArrayList <>();
    private UUID id;

    @BeforeEach
    void init() {
        testMechanic = new Mechanic( "Vasili", 3.4, new Calendar(), Speciality.MECHANIC );
        secondReshaper = new Mechanic( "Yakov", 5.9, new Calendar(), Speciality.RESHAPER );
        testReshaper = new Reshaper( "Sergei", 3.6, new Calendar(), Speciality.RESHAPER );
        testElectrician = new Electrician( "Ivan", 2.5, new Calendar(), Speciality.ELECTRICIAN );
        testPainter = new Painter( "Evgeny", 2.9, new Calendar(), Speciality.PAINTER );
        id = testMechanic.getId();

        masterList.add( testMechanic );
        masterList.add( secondReshaper );
        masterList.add( testElectrician );
        masterList.add( testPainter );
        masterList.add( testReshaper );

        when( mockRepo.getById( id ) ).thenReturn( testMechanic );
        when( mockRepo.findAll() ).thenReturn( masterList );
    }

    @Test
    void saveMaster() {
        this.masterService.saveMaster( this.testMechanic );
        verify( mockRepo, times( 1 ) ).save( testMechanic );
    }

    @Test
    void removeMaster() {
        this.masterService.deleteMaster( id );
        verify( mockRepo, times( 1 ) ).delete( id );
    }

    @Test
    void isBookedForDate() {
        this.masterService.setMasterForDate( id, LocalDate.now() );
        Assertions.assertEquals( true,
                this.masterService.getById( id ).getCalendar().isDateBooked( LocalDate.now() ) );
    }

    @Test
    void setMasterForDate() {
        Assertions.assertEquals( 0, testMechanic.getCalendar().getBookedDates().size() );
        this.masterService.setMasterForDate( id, LocalDate.now() );
        verify( mockRepo, times( 1 ) ).getById( id );
        Assertions.assertEquals( 1, testMechanic.getCalendar().getBookedDates().size() );
    }

    @Test
    void setBookedDateFree() {
        this.masterService.setMasterForDate( id, LocalDate.now() );
        this.masterService.setBookedDateFree( id, LocalDate.now() );
        Assertions.assertEquals( 0, testMechanic.getCalendar().getBookedDates().size() );
    }

    @Test
    void getByNameAndSpeciality() {
        Assertions.assertEquals( testPainter, this.masterService.getByNameAndSpeciality( "Evgeny", Speciality.PAINTER ) );
    }

    @Test
    void getBySpeciality() {
        Assertions.assertEquals( testElectrician, this.masterService.getBySpeciality( Speciality.ELECTRICIAN ) );
        verify( mockRepo, times( 1 ) ).findAll();
    }

    @Test
    void getAvailableSpecialities() {
        Assertions.assertEquals( 4, this.masterService.getAvailableSpecialities().size() );
    }

    @Test
    void getFreeBySpeciality() {
        this.masterService.setMasterForDate( id, LocalDate.now() );
        Assertions.assertThrows( RuntimeException.class, () -> this.masterService.getFreeBySpeciality( LocalDate.now(), Speciality.MECHANIC ) );
        Assertions.assertDoesNotThrow( () -> this.masterService.getFreeBySpeciality( LocalDate.now(), Speciality.ELECTRICIAN ) );
    }

    @Test
    void getMastersByAlphabet() {
        List <AbstractMaster> masters = this.masterService.getMastersByAlphabet();
        Assertions.assertEquals( "Sergei", masters.get( 2 ).getFullName() );
    }

    @Test
    void getFreeMasters() {
        testMechanic.getCalendar().setDateForBooking( LocalDate.now() );
        List <AbstractMaster> masters = this.masterService.getFreeMasters( LocalDate.now() );
        Assertions.assertEquals( 4, masters.size() );

    }

    @Test
    void getMastersBySpeciality() {
        List <AbstractMaster> masters = this.masterService.getMastersBySpeciality( Speciality.RESHAPER );
        Assertions.assertEquals( 2, masters.size() );


    }

    @Test
    void deleteMaster() {
        this.masterService.deleteMaster( id );
        verify( mockRepo, times( 1 ) ).delete( id );
    }
}
