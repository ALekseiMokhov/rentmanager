package main.entities.master;

import main.util.Calendar;

import java.time.LocalDate;
import java.util.UUID;

public interface Master {
    UUID getId();

    String getFullName();

    double getDailyPayment();

    Calendar getCalendar();

    Speciality getSpeciality();

    String toString();

}
