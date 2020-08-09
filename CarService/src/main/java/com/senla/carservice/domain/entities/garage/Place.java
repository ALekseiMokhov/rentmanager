package com.senla.carservice.domain.entities.garage;

import lombok.Data;
import lombok.NoArgsConstructor;
import util.calendar.Calendar;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue
    private UUID id;
    @Embedded
    private Calendar calendar;

    public Place(Calendar calendar) {
        this.id = UUID.randomUUID();
        this.calendar = calendar;

    }
    


}
