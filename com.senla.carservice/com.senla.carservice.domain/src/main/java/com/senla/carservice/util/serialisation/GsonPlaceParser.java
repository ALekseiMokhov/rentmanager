package com.senla.carservice.util.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.util.calendar.Calendar;
import com.senla.carservice.util.deserializers.CalendarDeserializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GsonPlaceParser {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter( Calendar.class, new CalendarDeserializer() )
            .create();
    private static final File FILE = new File( "./files/Json/PlacesJson.json" );

    public static List <Place> load() throws IOException {

        return parse( FILE ).stream()
                .map( s -> GSON.fromJson( s, Place.class ) )
                .collect( Collectors.toList() );
    }

    public static Place loadById(UUID id) throws IOException {
        return load().stream()
                .filter( p -> p.getId().equals( id ) )
                .findFirst()
                .get();
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


}
