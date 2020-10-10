package com.senla.carservice.dto.mappers;

import com.senla.carservice.entity.master.Speciality;
import org.springframework.stereotype.Component;

@Component
public class SpecialityMapper {

    public Speciality asSpeciality(String spec) {
        Speciality speciality = Speciality.valueOf(spec);
        return speciality;
    }

    public String asString(Speciality speciality) {
        return String.valueOf(speciality);
    }
}
