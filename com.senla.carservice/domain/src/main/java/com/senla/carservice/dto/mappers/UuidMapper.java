package com.senla.carservice.dto.mappers;

import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidMapper {

    public String asString(UUID id) {
        return id != null ? String.valueOf(id)
                : null;
    }

    public UUID asId(String id) {
        try {
            return id != null ? UUID.fromString(id)
                    : null;
        } catch (ParseException e) {
            /*TODO check handling*/
            throw new RuntimeException(e);
        }
    }
}