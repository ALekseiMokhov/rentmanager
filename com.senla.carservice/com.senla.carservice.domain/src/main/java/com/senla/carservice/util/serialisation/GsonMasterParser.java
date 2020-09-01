package com.senla.carservice.util.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.carservice.entity.master.*;
import com.senla.carservice.util.calendar.Calendar;
import com.senla.carservice.util.deserializers.CalendarDeserializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GsonMasterParser {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter( Calendar.class, new CalendarDeserializer() )
            .create();
    private final static File FILE = new File( "./files/Json/MastersJson.json" );


    public static List <IMaster> load() throws IOException {

        return parse( FILE ).stream()
                .map( s -> GSON.fromJson( s, parseClassType( s ) ) )
                .collect( Collectors.toList() );
    }

    public static List <IMaster> loadById(List <UUID> idList) throws IOException {

        List <IMaster> res = parse( FILE ).stream()
                .map( s -> GSON.fromJson( s, parseClassType( s ) ) )
                .collect( Collectors.toList() );
        return res.stream()
                .filter( m -> idList.contains( m.getId() ) )
                .collect( Collectors.toList() );
    }


    private static Class <? extends IMaster> parseClassType(String s) {
        Class clazz = null;
        String parsed = s.substring( s.lastIndexOf( ":" ) + 2, s.lastIndexOf( "\"" ) );
        switch (parsed) {
            case ("RESHAPER") -> clazz = Reshaper.class;
            case ("PAINTER") -> clazz = Painter.class;
            case ("ELECTRICIAN") -> clazz = Electrician.class;
            case ("MECHANIC") -> clazz = Mechanic.class;
            default -> throw new IllegalStateException( "Wrong type of master in json!!" );
        }
        return clazz;
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
