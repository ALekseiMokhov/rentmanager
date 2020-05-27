import java.time.LocalDate;

public class Order {
    private long id;
    private LocalDate dateBooked;
    private Master master;
    private Place place;
    private boolean isDone;


    public Order(LocalDate dateBooked, Master master, Place place) {
        this.dateBooked= dateBooked;
        this.master = master;
        this.place = place;
        this.isDone = false;
        if(place.isFreeForBooking( dateBooked )==false)   throw new IllegalArgumentException( "The date is already booked!") ;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public LocalDate getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(LocalDate dateBooked) {
        this.dateBooked = dateBooked;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateBooked=" + dateBooked +
                ", master=" + master +
                ", isDone=" + isDone +
                '}';
    }
}
