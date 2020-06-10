package main.entities.garage;

import main.util.Calendar;

import java.util.Objects;
import java.util.UUID;

public class Place {

    private UUID id;
    private Calendar calendar;

    public Place(Calendar calendar) {
        this.id = UUID.randomUUID();
        this.calendar = calendar;

    }

    public UUID getId() {
        return this.id;
    }

    public Calendar getCalendar() {
        return calendar;
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
