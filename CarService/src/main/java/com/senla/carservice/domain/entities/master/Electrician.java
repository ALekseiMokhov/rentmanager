package com.senla.carservice.domain.entities.master;

import util.Calendar;

import java.util.UUID;

public class Electrician extends AbstractMaster {

    public Electrician(String fullName, double dailiPayment, Calendar calendar, Speciality speciality) {
        super( fullName, dailiPayment, calendar, speciality );
    }

    public Electrician(String fullName, double dailiPayment, Calendar calendar, Speciality speciality, UUID id) {
        super( fullName, dailiPayment, calendar, speciality, id );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Electrician)) return false;
        Electrician that = (Electrician) o;
        return getId().equals( that.getId() ) &&
                getFullName().equals( that.getFullName() );
    }
}
