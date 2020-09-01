package com.senla.carservice.entity.master;


import com.senla.carservice.util.calendar.Calendar;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public abstract class AbstractMaster implements IMaster {
    @Id
    @GeneratedValue
    private UUID id;
    @Version
    private Long version;
    @Embedded
    private Calendar calendar;
    private String fullName;
    private double dailyPayment;
    @Enumerated
    private Speciality speciality;

    public AbstractMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.dailyPayment = dailyPayment;
        this.calendar = calendar;
        this.speciality = speciality;
    }

    public AbstractMaster(String fullName, double dailyPayment, Calendar calendar, Speciality speciality, UUID id) {

        this.fullName = fullName;
        this.dailyPayment = dailyPayment;
        this.calendar = calendar;
        this.speciality = speciality;
        this.id = id;
    }


}
