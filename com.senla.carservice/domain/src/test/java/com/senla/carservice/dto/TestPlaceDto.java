package com.senla.carservice.dto;

import com.senla.carservice.dto.mappers.PlaceMapper;
import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.util.calendar.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class TestPlaceDto {
    @Test
    void shouldMapPlaceToDto(){
        /*given*/
        Place place = new Place(new Calendar());
        place.setId(UUID.randomUUID());
        /*when*/
        PlaceDto placeDto = PlaceMapper.INSTANCE.placeToDto(place);
        /*then*/
        Assertions.assertNotNull(placeDto);
        Assertions.assertEquals(placeDto.getId(),String.valueOf(place.getId()));
    }

    @Test
    void shouldMapDtoToPlace(){
      /*given*/
        PlaceDto dto = new PlaceDto();
        dto.setCalendar(new Calendar());
        dto.getCalendar().setDateForBooking(LocalDate.now());
      /*when*/
        Place place = PlaceMapper.INSTANCE.dtoToPlace(dto);
      /*then*/
        Assertions.assertNotNull(place);
        Assertions.assertEquals(place.getCalendar().getBookedDates(), dto.getCalendar().getBookedDates());
    }
}
