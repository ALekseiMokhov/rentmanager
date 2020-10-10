package com.senla.carservice.dto;

import com.senla.carservice.dto.mappers.PlaceMapper;
import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.spring.TestConfig;
import com.senla.carservice.util.calendar.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestPlaceDto {

    @Autowired
    private PlaceMapper mapper;


    @Test
    void shouldMapPlaceToDto() {
        /*given*/
        Place place = new Place(new Calendar());
        place.setId(UUID.randomUUID());
        /*when*/
        PlaceDto placeDto = mapper.placeToDto(place);
        /*then*/
        Assertions.assertNotNull(placeDto);
        Assertions.assertEquals(placeDto.getId(), String.valueOf(place.getId()));
    }

    @Test
    void shouldMapDtoToPlace() {
        /*given*/
        PlaceDto dto = new PlaceDto();
        dto.setCalendar(new Calendar());
        dto.getCalendar().setDateForBooking(LocalDate.now());
        /*when*/
        Place place = mapper.dtoToPlace(dto);
        /*then*/
        Assertions.assertNotNull(place);
        Assertions.assertEquals(place.getCalendar().getBookedDates(), dto.getCalendar().getBookedDates());
    }
}
