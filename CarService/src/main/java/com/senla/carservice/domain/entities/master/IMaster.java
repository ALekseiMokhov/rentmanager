package entities.master;

import util.Calendar;

import java.util.UUID;

public interface IMaster {
    UUID getId();

    String getFullName();

    double getDailyPayment();

    Calendar getCalendar();

    Speciality getSpeciality();

    String toString();

}
