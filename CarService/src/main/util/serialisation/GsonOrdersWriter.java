package util.serialisation;

import com.google.gson.Gson;
import com.senla.carservice.domain.entities.order.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GsonOrdersWriter {

    private final static Gson GSON = new Gson();
    private final static File FILE = new File( "./files/Json/OrdersJson.txt" );


    public static void serializeOrder(Order order) throws IOException {
        removeOldOrder( order.getId() );

        String jsonFromEntity = GSON.toJson( order );
        try (BufferedWriter writer =
                     new BufferedWriter( new FileWriter( FILE, true ) )) {
            writer.append( "\n" );
            writer.append( jsonFromEntity );
            GsonPlacesWriter.serializePlace( order.getPlace() );
            GsonMasterWriter.serializeMasters( order.getMasters() );
        }

    }


    private static void removeOldOrder(UUID id) throws IOException {
        String existingId = String.valueOf( id );
        List <String> out = Files.lines( FILE.toPath() )
                .filter( line -> !line.startsWith( existingId ) )
                .collect( Collectors.toList() );
        Files.write( FILE.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING );
    }
}
