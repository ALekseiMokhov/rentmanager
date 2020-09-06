package util.serialisation;

import com.google.gson.Gson;
import com.senla.carservice.domain.entities.garage.Place;
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

public class GsonPlaceWriter {
    private static final Gson GSON = new Gson();
    private static final File FILE = new File( "./files/Json/PlacesJson.json" );

    public static void serializePlaces(List <Place> places) throws IOException {
        for (Place place : places) {
            serializePlace( place );
        }
    }

    public static void serializePlace(Place place) throws IOException {
        removeOldPlace( place.getId() );

        String jsonFromEntity = GSON.toJson( place );
        try (BufferedWriter writer =
                     new BufferedWriter( new FileWriter( FILE, true ) )) {
            writer.append( "\n" );
            writer.append( jsonFromEntity );
        }
        trimEmptyLines();

    }

    private static void removeOldPlace(UUID id) throws IOException {
        String existingId = "{\"id\":\"" + id;
        ;
        List <String> out = Files.lines( FILE.toPath() )
                .filter( line -> !line.startsWith( existingId ) )
                .collect( Collectors.toList() );
        Files.write( FILE.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING );
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
