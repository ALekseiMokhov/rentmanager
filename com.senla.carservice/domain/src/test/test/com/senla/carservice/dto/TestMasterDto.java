package com.senla.carservice.dto;


import com.senla.carservice.dto.mappers.interfaces.MasterMapper;
import com.senla.carservice.entity.master.Master;
import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.spring.TestConfig;
import com.senla.carservice.util.calendar.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestMasterDto {

    @Autowired
    private MasterMapper mapper;

    @Test
    void shouldMapMechanicToDto() {
        /*given*/
        Master master = new Master();
        master.setId(UUID.randomUUID());
        master.setFullName("Andrew");
        master.setCalendar(new Calendar());
        master.setDailyPayment(21.5);
        master.setSpeciality(Speciality.MECHANIC);
        /*when*/
        MasterDto masterDto = this.mapper.masterToDto(master);
        /*then*/
        Assertions.assertNotNull(masterDto);
        Assertions.assertEquals("MECHANIC", masterDto.getSpeciality());
        Assertions.assertEquals(master.getId().toString(), masterDto.getId());
        Assertions.assertEquals(master.getFullName(), masterDto.getFullName());
    }

    @Test
    void shouldMapElectricianToDto() {
        /*given*/
        Master master = new Master();
        master.setId(UUID.randomUUID());
        master.setFullName("Sergey");
        master.setCalendar(new Calendar());
        master.setDailyPayment(19.5);
        master.setSpeciality(Speciality.ELECTRICIAN);
        /*when*/
        MasterDto masterDto = this.mapper.masterToDto(master);
        /*then*/
        Assertions.assertNotNull(masterDto);
        Assertions.assertEquals("ELECTRICIAN", masterDto.getSpeciality());
        Assertions.assertEquals(master.getId().toString(), masterDto.getId());
        Assertions.assertEquals(master.getFullName(), masterDto.getFullName());
    }

    @Test
    void shouldMapPainterDtoToPainter() {
        /*given*/
        MasterDto masterDto = new MasterDto();
        masterDto.setId(UUID.randomUUID().toString());
        masterDto.setCalendar(new Calendar());
        masterDto.setFullName("Basiliy");
        masterDto.setDailyPayment(33.1);
        masterDto.setSpeciality("PAINTER");
        /*when*/
        Master painter = mapper.masterFromDto(masterDto);
        /*then*/
        Assertions.assertEquals(painter.getFullName(), masterDto.getFullName());
        Assertions.assertEquals(painter.getId().toString(), masterDto.getId());
    }
}
