package com.senla.carservice.domain.entities.master;

import util.calendar.Calendar;

import java.util.UUID;

public class Painter extends AbstractMaster {
    public Painter(String fullName, double dailiPayment, Calendar calendar, Speciality speciality) {
        super( fullName, dailiPayment, calendar, speciality );
    }

    public Painter(String fullName, double dailiPayment, Calendar calendar, Speciality speciality, UUID id) {
        super( fullName, dailiPayment, calendar, speciality, id );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Painter)) return false;
        Painter that = (Painter) o;
        return getId().equals( that.getId() ) &&
                getFullName().equals( that.getFullName() );
    }
}
