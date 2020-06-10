package entities.master;

import util.Calendar;

public class Electrician extends AbstractMaster {

    public Electrician(String fullName, double dailiPayment, Calendar calendar, Speciality speciality) {
        super( fullName, dailiPayment, calendar, speciality );
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
