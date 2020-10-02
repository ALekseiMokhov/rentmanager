package com.senla.carservice.dto;

import com.senla.carservice.entity.master.Speciality;
import com.senla.carservice.util.calendar.Calendar;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Version;
import java.util.UUID;

@Data
public class MechanicDto  {
    private String id;
    private Calendar calendar;
    private String fullName;
    private double dailyPayment;
    private String speciality;
}
