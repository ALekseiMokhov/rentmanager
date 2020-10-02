package com.senla.carservice.dto;

import com.senla.carservice.util.calendar.Calendar;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
public class PlaceDto {

    private String id;
    private Calendar calendar;
}
