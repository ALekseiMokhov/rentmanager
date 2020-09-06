package util.csv;


import com.senla.carservice.domain.entities.master.IMaster;

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

public class CsvMasterWriter {
    private static final File FILE = new File( "./files/master.csv" );

    public static void writeMaster(IMaster master) throws IOException {
        removeOldMaster( master.getId() );

        try (BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( FILE, true ) )) {
            bufferedWriter.append( "\n" );
            bufferedWriter.append( master.getFullName() + "," );
            bufferedWriter.append( master.getDailyPayment() + "," );
            bufferedWriter.append( master.getSpeciality() + "," );
            bufferedWriter.append( (master.getId()) + "," );
            for (LocalDate bookedDate : master.getCalendar().getBookedDates().keySet()) {
                bufferedWriter.append( (bookedDate) + "," );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeMasters(List <IMaster> masters) throws IOException {

        for (IMaster master : masters) {
            writeMaster( master );
        }

    }

    private static void removeOldMaster(UUID id) throws IOException {
        String existingId = String.valueOf( id );
        List <String> out = Files.lines( FILE.toPath() )
                .filter( line -> !line.startsWith( existingId ) )
                .collect( Collectors.toList() );
        Files.write( FILE.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING );
    }

}
