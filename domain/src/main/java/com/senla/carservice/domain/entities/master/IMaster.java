package com.senla.carservice.domain.entities.master;

import util.calendar.Calendar;

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
