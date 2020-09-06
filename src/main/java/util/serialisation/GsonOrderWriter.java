package util.serialisation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.order.Order;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GsonOrderWriter {

    private static final Gson GSON = new Gson();
    private static final File FILE = new File( "./files/Json/OrdersJson.json" );

    public static void serializeOrders(List <Order> list) throws IOException {
        for (Order order : list) {
            serializeOrder( order );
        }
    }


    public static void serializeOrder(Order order) throws IOException {
        removeOldOrder( order.getId() );

        JsonObject object = new JsonObject();
        object.addProperty( "id", String.valueOf( order.getId() ) );
        object.addProperty( "status", String.valueOf( order.getStatus() ) );
        object.addProperty( "dateBooked", String.valueOf( order.getDateBooked() ) );
        object.addProperty( "startOfExecution", String.valueOf( order.getStartOfExecution() ) );
        object.addProperty( "finishOfExecution", String.valueOf( order.getFinishOfExecution() ) );
        object.addProperty( "masters", iMasterIdAppend( order.getMasters() ) );
        object.addProperty( "place", String.valueOf( order.getPlace().getId() ) );


        String jsonFromEntity = GSON.toJson( object );
        try (BufferedWriter writer =
                     new BufferedWriter( new FileWriter( FILE, true ) )) {
            writer.append( "\n" );
            writer.append( jsonFromEntity );
            GsonPlaceWriter.serializePlace( order.getPlace() );
            GsonMasterWriter.serializeMasters( order.getMasters() );
        }
        trimEmptyLines();
    }


    private static void removeOldOrder(UUID id) throws IOException {
        String existingId = "{\"id\":\"" + id;
        List <String> out = Files.lines( FILE.toPath() )
                .filter( line -> !line.startsWith( existingId ) )
                .collect( Collectors.toList() );
        Files.write( FILE.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING );
    }

    private static String iMasterIdAppend(List <IMaster> list) {
        StringBuilder builder = new StringBuilder();
        builder.append( "{" );
        for (IMaster master : list) {
            builder.append( master.getId() + "," );

        }
        builder.append( "}" );
        return builder.toString();
    }

    private static void trimEmptyLines() {
        try {

            List <String> lines = FileUtils.readLines( FILE );

            Iterator <String> i = lines.iterator();
            while (i.hasNext()) {
                String line = i.next();
                if (line.trim().isEmpty())
                    i.remove();
            }

            FileUtils.writeLines( FILE, lines );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
