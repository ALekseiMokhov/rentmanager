package main.entities.master;

public class Painter extends AbstractMaster {
    public Painter(String fullName, double dailyPayment) {
        super( fullName, dailyPayment );
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
