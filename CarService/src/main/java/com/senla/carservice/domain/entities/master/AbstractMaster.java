package com.senla.carservice.domain.entities.master;

import lombok.Data;
import lombok.NoArgsConstructor;
import util.calendar.Calendar;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor

public abstract class AbstractMaster implements IMaster {
    @Id
    @GeneratedValue
    private UUID id;
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
