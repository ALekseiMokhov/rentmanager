package com.senla.carservice.service;

import com.senla.carservice.dto.MasterDto;
import com.senla.carservice.dto.mappers.MasterMapperImpl;
import com.senla.carservice.entity.master.Master;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.repository.jpa.MasterJpaRepository;
import com.senla.carservice.util.calendar.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class TestMasterService {
    private final IGenericRepository mockRepo
            = Mockito.mock(MasterJpaRepository.class);
    @InjectMocks
    private MasterService masterService;
    @Mock
    private MasterMapperImpl masterMapper;
    private Master testMechanic;
    private Master secondReshaper;
    private Master testReshaper;
    private Master testElectrician;
    private Master testPainter;
    private MasterDto testMechanicDto;
    private MasterDto testPainterDto;
    private final List<Master> masterList = new ArrayList<>();
    private UUID id;

    @BeforeEach
    void init() {
        testMechanic = new Master("Vasili", 3.4, new Calendar(), Speciality.MECHANIC);
        secondReshaper = new Master("Yakov", 5.9, new Calendar(), Speciality.RESHAPER);
        testReshaper = new Master("Sergei", 3.6, new Calendar(), Speciality.RESHAPER);
        testElectrician = new Master("Ivan", 2.5, new Calendar(), Speciality.ELECTRICIAN);
        testPainter = new Master("Evgeny", 2.9, new Calendar(), Speciality.PAINTER);
        id = testMechanic.getId();

        masterList.add(testMechanic);
        masterList.add(secondReshaper);
        masterList.add(testElectrician);
        masterList.add(testPainter);
        masterList.add(testReshaper);

        testMechanicDto = new MasterDto();
        testPainterDto = new MasterDto();

        when(mockRepo.getById(id)).thenReturn(testMechanic);
        when(mockRepo.findAll()).thenReturn(masterList);
        /**/
    }

    @Test
    void verifyRepositorySaveMaster() {
        when(masterMapper.masterFromDto(any(MasterDto.class))).thenReturn(testMechanic);
        this.masterService.saveMaster(this.testMechanicDto);
        verify(mockRepo, times(1)).update(testMechanic);
    }

    @Test
    void verifyRepositoryRemoveMaster() {
        this.masterService.deleteMaster(id);

        verify(mockRepo, times(1)).delete(id);
    }

    @Test
    void givenIdANdDateShouldReturnIsMasterBooked() {
        this.masterService.setMasterForDate(id, LocalDate.now());

        Boolean result = testMechanic.getCalendar().isDateBooked(LocalDate.now());

        Assertions.assertEquals(true, result);
    }

    @Test
    void givenDateShouldSetMasterForTheDate() {
        this.masterService.setMasterForDate(id, LocalDate.now());

        verify(mockRepo, times(1)).getById(id);
        Assertions.assertEquals(1, testMechanic.getCalendar().getBookedDates().size());
    }

    @Test
    void givenDateShouldSetMasterFree() {
        this.masterService.setMasterForDate(id, LocalDate.now());
        this.masterService.setBookedDateFree(id, LocalDate.now());

        Assertions.assertEquals(0, testMechanic.getCalendar().getBookedDates().size());
    }

    @Test
    void givenNameAndSpecialityShouldFIndMaster() {
        this.masterService.getByNameAndSpeciality("Evgeny", Speciality.PAINTER);
        verify(mockRepo,times(1)).findAll();
        verify(masterMapper,times(1)).masterToDto(any(Master.class));
    }

    @Test
    void givenSpecialityShouldFIndAllMasters() {
        this.masterService.getBySpeciality(Speciality.ELECTRICIAN);
        verify(mockRepo, times(1)).findAll();

    }

    @Test
    void shouldReturnAvailableSpecialities() {
        Assertions.assertEquals(4, this.masterService.getAvailableSpecialities().size());
    }

    @Test
    void givenIdAndDateShouldBookMaster() {
        this.masterService.setMasterForDate(id, LocalDate.now());

        Assertions.assertDoesNotThrow(() -> this.masterService.getFreeBySpeciality(LocalDate.now(), Speciality.ELECTRICIAN));
    }

    @Test
    void shouldGetAllFreeMasters() {
        testMechanic.getCalendar().setDateForBooking(LocalDate.now());

        List<MasterDto> masters = this.masterService.getFreeMasters(LocalDate.now());
       verify(masterMapper,times(1)).mastersToDto(any(List.class));

    }

    @Test
    void givenSpecialityShouldGetChosenMasters() {
        this.masterService.getMastersBySpeciality(Speciality.RESHAPER);
        /*Assertions.assertTrue(masterMapper.mastersToDto(masterList));*/
        /*TODO correct test!*/



    }

    @Test
    void verifyRepositoryDeleteMaster() {
        this.masterService.deleteMaster(id);

        verify(mockRepo, times(1)).delete(id);
    }


}
