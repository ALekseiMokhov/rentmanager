package com.senla.carservice.entity.master;

import com.senla.carservice.util.calendar.Calendar;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Reshaper extends AbstractMaster {
    public Reshaper(String fullName, double dailiPayment, Calendar calendar, Speciality speciality) {
        super(fullName, dailiPayment, calendar, speciality);
    }

    public Reshaper(String fullName, double dailiPayment, Calendar calendar, Speciality speciality, UUID id) {
        super(fullName, dailiPayment, calendar, speciality, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reshaper)) return false;
        Reshaper that = (Reshaper) o;
        return getId().equals(that.getId()) &&
                getFullName().equals(that.getFullName());
    }
}
