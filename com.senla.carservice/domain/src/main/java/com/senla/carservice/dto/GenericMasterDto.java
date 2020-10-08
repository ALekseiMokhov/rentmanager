package com.senla.carservice.dto;

import com.senla.carservice.util.calendar.Calendar;

public interface GenericMasterDto {
    void setDailyPayment(double dailyPayment);
    void setFullName(String fullName);
    void setCalendar(Calendar calendar);
    void setSpeciality(String speciality);
    void setId(String id);

}
