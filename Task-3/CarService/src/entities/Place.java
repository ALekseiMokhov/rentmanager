package entities;

import java.time.LocalDate;

public class Place {
    private int id;
    private boolean isFree = true;
    private LocalDate [] bookedDates = new LocalDate[365];

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean isFreeForBooking(LocalDate dateToCheck){
        for (LocalDate bookedDate : bookedDates) {
             if(bookedDate!=null && bookedDate.compareTo( dateToCheck )==0) return false;
        }
        return true;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public LocalDate[] getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(LocalDate[] bookedDates) {
        this.bookedDates = bookedDates;
    }
}
