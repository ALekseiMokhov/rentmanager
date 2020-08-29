package util.serialisation;

import com.google.gson.Gson;
import com.senla.carservice.domain.entities.master.IMaster;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class GsonMasterWriter {
    private static final Gson GSON = new Gson();
    private static final File FILE = new File( "./files/Json/MastersJson.json" );

    public static void serializeMasters(List <IMaster> list) throws IOException {
        for (IMaster master : list) {
            serializeMaster( master );
        }
    }


    public static void serializeMaster(IMaster master) throws IOException {
        removeOldMaster( master.getFullName() );

        String jsonFromEntity = GSON.toJson( master );
        try (BufferedWriter writer =
                     new BufferedWriter( new FileWriter( FILE, true ) )) {
            writer.append( "\n" );
            writer.append( jsonFromEntity );
        }
        trimEmptyLines();

    }


    private static void removeOldMaster(String fullname) throws IOException {
        String jsonFullname = "{\"fullname\":\"" + fullname;

        List <String> out = Files.lines( FILE.toPath() )
                .filter( line -> !line.startsWith( fullname ) )
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
