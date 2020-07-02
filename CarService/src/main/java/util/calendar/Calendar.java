package util;

import java.time.LocalDate;
import java.util.HashMap;

public class Calendar {

    private HashMap <LocalDate, Boolean> bookedDates;

    public Calendar() {
        this.bookedDates = new HashMap <>();
    }

    public HashMap <LocalDate, Boolean> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(HashMap <LocalDate, Boolean> bookedDates) {
        this.bookedDates = bookedDates;
    }

    public void setDateForBooking(LocalDate date) {
        bookedDates.put( date, true );
    }

    public void deleteBookedDate(LocalDate date) {
        bookedDates.remove( date );
    }

    public boolean isDateBooked(LocalDate date) {
        return bookedDates.getOrDefault( date, false );
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "bookedDates=" + bookedDates.keySet() +
                '}';
    }
}
