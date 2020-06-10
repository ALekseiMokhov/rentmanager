package entities.master;

import util.Calendar;

import java.util.Objects;
import java.util.UUID;

public abstract class AbstractMaster implements IMaster {
    private UUID id;
    private Calendar calendar;
    private String fullName;
    private double dailyPayment;
    private Speciality speciality;

    public AbstractMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.dailyPayment = dailyPayment;
        this.calendar = calendar;
        this.speciality = speciality;
    }


    public double getDailyPayment() {
        return this.dailyPayment;
    }

    public void setDailyPayment(double dailyPayment) {
        this.dailyPayment = dailyPayment;
    }


    public UUID getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    @Override
    public Speciality getSpeciality() {
        return speciality;
    }

    @Override
    public int hashCode() {
        return Objects.hash( getId(), getFullName(), dailyPayment );
    }

    @Override
    public String toString() {
        return "Master { " + speciality +
                ", fullName='" + fullName + '\'' +
                ", booked dates= " + calendar.getBookedDates() +
                '}';
    }
}
