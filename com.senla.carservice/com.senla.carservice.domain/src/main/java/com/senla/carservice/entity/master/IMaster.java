package com.senla.carservice.entity.master;


import com.senla.carservice.util.calendar.Calendar;

import java.util.UUID;

public interface IMaster {
    UUID getId();

    String getFullName();

    double getDailyPayment();

    Calendar getCalendar();

    void setCalendar(Calendar calendar);

    Speciality getSpeciality();

    String toString();

}
