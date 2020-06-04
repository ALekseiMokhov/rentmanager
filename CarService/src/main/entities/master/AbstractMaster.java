package main.entities.master;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public abstract class AbstractMaster implements Master {
    private UUID id;
    private HashMap <LocalDate, Boolean> bookedDates = new HashMap <>();
    private String fullName;
    private double dailyPayment;

    public AbstractMaster(String fullName, double dailyPayment) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.dailyPayment = dailyPayment;
    }


    public double getDailyPayment() {
        return this.dailyPayment;
    }

    public void setDailyPayment(double dailyPayment) {
        this.dailyPayment = dailyPayment;
    }


    public boolean isFreeForDate(LocalDate date) {
        return this.bookedDates.getOrDefault( date, true );
    }

    public void bookMaster(LocalDate date) {
        this.bookedDates.put( date, false );
    }

    public void unBookMaster(LocalDate date) {
        this.bookedDates.put( date, true );
    }

    public UUID getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }


    @Override
    public int hashCode() {
        return Objects.hash( getId(), getFullName(), dailyPayment );
    }

    @Override
    public String toString() {
        return "Master { " + this.getClass() +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
