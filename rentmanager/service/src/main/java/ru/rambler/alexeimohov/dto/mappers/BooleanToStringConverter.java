package ru.rambler.alexeimohov.dto.mappers;

import org.springframework.stereotype.Component;

@Component
public class BooleanToStringConverter {

    public Boolean asBoolean(String var) {
        if (var.equals("true")) {
            return true;
        }
        return false;
    }

    public String asString(Boolean var) {
        if (var == true) {
            return "true";
        }
        return "false";
    }
}
