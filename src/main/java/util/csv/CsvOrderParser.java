package util.csv;

import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.domain.entities.order.OrderStatus;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CsvOrderParser {

    private static final File FILE = new File( "./files/order.csv" );

    public static List <Order> load() throws IOException {
        List <Order> res = parseOrdersFromString( intermediateList( parse( FILE ) ) );
        return res;

    }

    private static String parse(File file) throws IOException {

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

    private static List <String> intermediateList(String result) {
        List <String> intermidiate = Arrays.asList( result.split( " " ) );
        return intermidiate;
    }

    private static List <Order> parseOrdersFromString(List <String> input) throws IOException {
        List <Order> res = new ArrayList <>();

        List <List <String>> list = input.stream()
                .map( s -> Arrays.asList( s.split( "," ) ) )
                .collect( Collectors.toList() );
        for (List <String> var : list) {
            Order order = new Order(
                    UUID.fromString( var.get( 0 ) )
                    , LocalDate.parse( var.get( 1 ) )
                    , LocalDate.parse( var.get( 2 ) )
                    , CsvPlaceParser.loadById( UUID.fromString( var.get( 3 ) ) ).get()
                    , CsvMasterParser.loadMastersById( parseMastersId( var.subList( 4, var.size() ) ) )
            );
            order.setStatus( order.getFinishOfExecution() == null ? OrderStatus.MANAGED : OrderStatus.COMPLETED );
            res.add( order );
        }

        return res;
    }

    private static List <UUID> parseMastersId(List <String> input) {
        return input.stream()
                .map( s -> UUID.fromString( s ) )
                .collect( Collectors.toList() );
    }


}
