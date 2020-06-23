package util.csv;

import com.senla.carservice.domain.entities.garage.Place;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class CsvPlaceWriter {
    public static void writePlace(Place place) throws IOException {

        File file = new File( "./files/place.csv" );
     
        try (BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( file ,true) )) {
            bufferedWriter.append( "\n" );
            bufferedWriter.append(( place.getId() ) + "," );
            for (LocalDate bookedDate : place.getCalendar().getBookedDates().keySet()) {
                bufferedWriter.append(( bookedDate ) + "," );
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
}
