package com.senla.carservice.domain.entities.master;

import util.Calendar;

public class Painter extends AbstractMaster {
    public Painter(String fullName, double dailiPayment, Calendar calendar, Speciality speciality) {
        super( fullName, dailiPayment, calendar, speciality );
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
