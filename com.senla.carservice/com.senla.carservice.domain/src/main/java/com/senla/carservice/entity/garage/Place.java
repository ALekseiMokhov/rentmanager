package com.senla.carservice.entity.garage;


import com.senla.carservice.util.calendar.Calendar;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue
    private UUID id;

    @Embedded
    private Calendar calendar;

    public Place(Calendar calendar) {
        this.calendar = calendar;

    }
}
