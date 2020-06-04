package main.entities.master;

public class Electrician extends AbstractMaster {

    public Electrician(String fullName, double dailiPayment) {
        super( fullName, dailiPayment );
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
