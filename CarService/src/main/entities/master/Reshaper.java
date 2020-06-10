package main.entities.master;

import main.util.Calendar;

public class Reshaper extends AbstractMaster {
    public Reshaper(String fullName, double dailiPayment, Calendar calendar, Speciality speciality) {
        super( fullName, dailiPayment, calendar, speciality );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reshaper)) return false;
        Reshaper that = (Reshaper) o;
        return getId().equals( that.getId() ) &&
                getFullName().equals( that.getFullName() );
    }
}
