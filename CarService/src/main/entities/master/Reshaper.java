package main.entities.master;

public class Reshaper extends AbstractMaster {
    public Reshaper(String fullName, double dailyPayment) {
        super( fullName, dailyPayment );
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
