package ru.rambler.alexeimohov.dto.mappers;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateMapper {


    public String asString(LocalDate date) {
        return new SimpleDateFormat( "yyyy-MM-dd" )
                .format( date );
    }

    public LocalDate asDate(String date) {

        return LocalDate.parse( date );


    }
    public String timeAsString(LocalDateTime dateTime) {
        return  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") 
                .format( dateTime );
    }

    public LocalDateTime asDateTime(String dateTime) {

        return LocalDateTime.parse( dateTime );


    }
}
