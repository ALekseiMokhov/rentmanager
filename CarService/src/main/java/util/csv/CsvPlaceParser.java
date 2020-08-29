package util.csv;

import com.senla.carservice.domain.entities.garage.Place;
import util.calendar.Calendar;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class CsvPlaceParser {
    private static final File FILE = new File( "./files/place.csv" );

    public static Optional <Place> loadById(UUID id) throws IOException {
        return load().stream()
                .filter( p -> p.getId().equals( id ) )
                .findFirst();
    }


    public static List <Place> load() throws IOException {

        List <Place> list = listOfStrings( parse( FILE ) );


        return list;

    }

    private static String parse(File file)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader( new InputStreamReader(
                new FileInputStream( file )
        ) )) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    resultStringBuilder.append( line ).append( " " );
                }
            }
        }
        return resultStringBuilder.toString();
    }

    private static List <Place> listOfStrings(String input) {
        List <String> list = Arrays.asList( input.split( " " ) );

        return list.stream()
                .map( s -> parsePlace( s ) )
                .collect( Collectors.toList() );
    }

    private static Place parsePlace(String input) {
        List <String> list = Arrays.asList( input.split( "," ) );
        Place place = new Place( new Calendar() );
        place.setId( UUID.fromString( list.get( 0 ) ) );
        if (list.size() > 1) {
            place.getCalendar().setBookedDates( parseCalendar( list.subList( 1, list.size() ) ) );
        }

        return place;
    }

    private static HashMap <LocalDate, Boolean> parseCalendar(List <String> input) {
        HashMap <LocalDate, Boolean> datesBooked = new HashMap <>();
        for (String s : input) {
            datesBooked.put( LocalDate.parse( s ), true );
        }
        return datesBooked;

    }
}
