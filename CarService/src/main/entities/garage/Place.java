package main.entities.garage;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Place {
    private UUID id;
    private HashMap <LocalDate, Boolean> bookedDates = new HashMap <>();

    public Place() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }

    public boolean isFreeForBooking(LocalDate dateToCheck) {
        return this.bookedDates.getOrDefault( dateToCheck, true );
    }

    public void bookPlace(LocalDate date) {
        this.bookedDates.put( date, false );
    }

    public void unBookPlace(LocalDate date) {
        this.bookedDates.put( date, true );
    }

    public HashMap <LocalDate, Boolean> getBookedDates() {
        return this.bookedDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        return getId().equals( place.getId() );
    }

    @Override
    public int hashCode() {
        return Objects.hash( getId() );
    }
}
