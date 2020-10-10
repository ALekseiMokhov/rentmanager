package com.senla.carservice.dto;

import com.senla.carservice.util.calendar.Calendar;
import lombok.Data;

@Data
public class MasterDto  {
    private String id;
    private Calendar calendar;
    private String fullName;
    private double dailyPayment;
    private String speciality;


}
