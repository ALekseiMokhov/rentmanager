package com.senla.carservice.util.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.senla.carservice.entity.order.Order;
import com.senla.carservice.entity.order.OrderStatus;
import com.senla.carservice.util.calendar.Calendar;
import com.senla.carservice.util.deserializers.CalendarDeserializer;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GsonOrderParser {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter( Calendar.class, new CalendarDeserializer() )
            .create();

    private final static File FILE = new File( "./files/Json/OrdersJson.json" );


    public static List <Order> load() throws IOException {
        List <Order> res = parse( FILE ).stream()
                .map( s -> {
                    try {
                        return deserialize( s );
                    } catch (IOException e) {
                        System.err.println( "Cant deserialize order!" );
                    }
                    return null;
                } ).collect( Collectors.toList() );


        return res;
    }


    private static List <String> parse(File file) throws IOException {
        List <String> list = new ArrayList <>();
        try (BufferedReader br
                     = new BufferedReader( new InputStreamReader(
                new FileInputStream( file )
        ) )) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    list.add( line );
                }
            }
        }
        return list;
    }

    private static Order deserialize(String input) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse( input ).getAsJsonObject();
        Order order = new Order(
                UUID.fromString( object.get( "id" ).getAsString() )
                , LocalDate.parse( object.get( "dateBooked" ).getAsString() )
                , LocalDate.parse( object.get( "startOfExecution" ).getAsString() )
                , GsonPlaceParser.loadById( convertPlaceId( object.get( "place" ).getAsString() ) )
                , GsonMasterParser.loadById( convertImastersId( object.get( "masters" ).getAsString() ) ) );

        order.setStatus( OrderStatus.valueOf( object.get( "status" ).getAsString() ) );
        if (order.getStatus() == OrderStatus.COMPLETED) {
            order.setFinishOfExecution( LocalDate.parse( object.get( "finishOfExecution" ).getAsString() ) );
        }

        return order;
    }

    private static List <UUID> convertImastersId(String input) {
        List <String> list = Arrays.asList( input
                .replace( "\"", "" )
                .replace( "\"", "" )
                .replace( "{", "" )
                .replace( "}", "" )
                .split( "," ) );

        return list.stream()
                .map( s -> UUID.fromString( s ) )
                .collect( Collectors.toList() );
    }

    private static UUID convertPlaceId(String input) {
        return UUID.fromString( input.replace( "\"", "" ) );
    }

}
