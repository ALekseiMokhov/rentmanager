package com.senla.carservice.dto;


import com.senla.carservice.dto.mappers.MasterMapper;
import com.senla.carservice.entity.master.*;
import com.senla.carservice.util.calendar.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class TestMasterDto {
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
      MasterDto masterDto = MasterMapper.INSTANCE.masterToDto( master);
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
       MasterDto electricianDto = MasterMapper.INSTANCE.masterToDto(master);
        /*then*/
        Assertions.assertNotNull(electricianDto);
        Assertions.assertEquals("ELECTRICIAN", electricianDto.getSpeciality());
        Assertions.assertEquals(master.getId().toString(), electricianDto.getId());
        Assertions.assertEquals(master.getFullName(), electricianDto.getFullName());
    }

    @Test
    void shouldMapPainterDtoToPainter() {
        /*given*/
        MasterDto painterDto = new MasterDto();
        painterDto.setId(UUID.randomUUID().toString());
        painterDto.setCalendar(new Calendar());
        painterDto.setFullName("Basiliy");
        painterDto.setDailyPayment(33.1);
        painterDto.setSpeciality("PAINTER");
        /*when*/
        Master painter = MasterMapper.INSTANCE.masterFromDto(painterDto);
        /*then*/
        Assertions.assertEquals(painter.getFullName(), painterDto.getFullName());
        Assertions.assertEquals(painter.getId().toString(), painterDto.getId());
    }
}
