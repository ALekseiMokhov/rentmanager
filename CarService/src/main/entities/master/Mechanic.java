package main.entities.master;

import main.util.Calendar;

public class Mechanic extends AbstractMaster {
    public Mechanic(String fullName, double dailiPayment, Calendar calendar, Speciality speciality) {
        super( fullName, dailiPayment, calendar, speciality );
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mechanic)) return false;
        Mechanic that = (Mechanic) o;
        return getId().equals( that.getId() ) &&
                getFullName().equals( that.getFullName() );
    }
}
