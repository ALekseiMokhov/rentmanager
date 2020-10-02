package com.senla.carservice.dto;

import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.util.calendar.Calendar;
import lombok.Data;

import java.util.UUID;
@Data
public class ReshaperDto {
    private String id;
    private Calendar calendar;
    private String fullName;
    private double dailyPayment;
    private String speciality;
}
