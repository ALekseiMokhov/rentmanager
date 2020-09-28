package com.senla.carservice.util.csv;


import com.senla.carservice.entity.garage.Place;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CsvPlaceWriter {
    private static final File FILE = new File( "./files/place.csv" );

    public static void writePlace(Place place) throws IOException {
        removeOldPlace( place.getId() );     /* Updating place if its already written*/

        try (BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( FILE, true ) )) {

            bufferedWriter.append( "\n" );
            bufferedWriter.append( (place.getId()) + "," );
            for (LocalDate bookedDate : place.getCalendar().getBookedDates().keySet()) {
                bufferedWriter.append( (bookedDate) + "," );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void writePlaces(List <Place> places) throws IOException {

        for (Place place : places) {
            writePlace( place );

        }

    }

    private static void removeOldPlace(UUID id) throws IOException {
        String existingId = String.valueOf( id );
        List <String> out = Files.lines( FILE.toPath() )
                .filter( line -> !line.startsWith( existingId ) )
                .collect( Collectors.toList() );
        Files.write( FILE.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING );
    }


}
