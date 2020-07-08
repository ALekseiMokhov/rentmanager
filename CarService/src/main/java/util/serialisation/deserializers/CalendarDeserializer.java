package util.serialisation.deserializers;

import com.google.gson.*;
import util.calendar.Calendar;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;

public class CalendarDeserializer implements JsonDeserializer <Calendar> {
    @Override
    public Calendar deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        Calendar calendar = new Calendar();

        JsonObject jsonObject = json.getAsJsonObject();
        String stringToParse = String.valueOf( jsonObject.entrySet() );

        calendar.setBookedDates( parseDates( stringToParse ) );

        return calendar;
    }

    private HashMap <LocalDate, Boolean> parseDates(String input) {
        HashMap <LocalDate, Boolean> res = new HashMap <>();
        int start = input.indexOf( "{" ) ;
        int end = input.indexOf( "}" ) ;
        String intermediate = input.substring( start+1, end ) ;
        if (intermediate.isEmpty()) {
            return res;
        }
        String [] entries = intermediate.split( "," ) ;
        for (String entry : entries) {
           res.put( LocalDate.parse(entry.substring( 1,11 )  ),true );
        }
        return res;
    }
}

