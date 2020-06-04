package main.entities.master;

public class Mechanic extends AbstractMaster {
    public Mechanic(String fullName, double dailyPayment) {
        super( fullName, dailyPayment );
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
