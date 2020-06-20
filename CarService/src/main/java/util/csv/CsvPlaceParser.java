package util.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class CsvPlaceParser  {



    public static List <UUID> load() throws IOException {

        List <UUID> list = listOfId( parse() );

        return list;

    }

    private static String parse() throws IOException {
        Class clazz = CsvPlaceParser.class;
        InputStream inputStream = clazz.getResourceAsStream( "/csv/place.csv" );
        String result = readFromInputStream( inputStream );
        return result;
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader( new InputStreamReader( inputStream ) )) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append( line ).append( " " );
            }
        }
        return resultStringBuilder.toString();
    }

    private static List <UUID> listOfId(String input) {
        List <String> list = Arrays.asList( input.split( " " ) );
        return list.stream()
                .map( s -> UUID.fromString( s ) )
                .collect( Collectors.toList() );
    }
}
