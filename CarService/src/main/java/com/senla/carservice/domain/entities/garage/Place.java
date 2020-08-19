package com.senla.carservice.domain.entities.garage;

import lombok.Data;
import lombok.NoArgsConstructor;
import util.calendar.Calendar;

import java.util.UUID;

/*@Entity*/
@Data
@NoArgsConstructor
public class Place {
    /*@Id
    @GeneratedValue*/
    private UUID id;
   /* @Version
    private Long version;*/
  /*  @Embedded*/
    private Calendar calendar;

    public Place(Calendar calendar) {
        this.id = UUID.randomUUID();
        this.calendar = calendar;

    }


}
