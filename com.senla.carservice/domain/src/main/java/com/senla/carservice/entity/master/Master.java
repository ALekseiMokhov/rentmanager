package com.senla.carservice.entity.master;


import com.google.gson.annotations.Expose;
import com.senla.carservice.util.calendar.Calendar;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "masters")
public  class Master {
    @Id
    @GeneratedValue
    private UUID id;
    @Version
    @Expose
    private Long version;
    @Embedded
    private Calendar calendar;
    private String fullName;
    private double dailyPayment;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    public Master(String fullName, double dailyPayment, Calendar calendar, Speciality speciality) {
        this.fullName = fullName;
        this.dailyPayment = dailyPayment;
        this.calendar = calendar;
        this.speciality = speciality;
    }

    public Master(String fullName, double dailyPayment, Calendar calendar, Speciality speciality, UUID id) {

        this.fullName = fullName;
        this.dailyPayment = dailyPayment;
        this.calendar = calendar;
        this.speciality = speciality;
        this.id = id;
    }


}
