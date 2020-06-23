package util.csv;


import com.senla.carservice.domain.entities.master.IMaster;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CsvMasterWriter {

    public static void writeMaster(IMaster master) throws IOException {

        File file = new File( "./files/master.csv" );

        try (BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( file ,true) )) {
            bufferedWriter.append( "\n" );
            bufferedWriter.append( master.getFullName() + "," );
            bufferedWriter.append( master.getDailyPayment() + "," );
            bufferedWriter.append(  master.getSpeciality() + ","  ) ;
            bufferedWriter.append( ( master.getId()) + "," );
            for (LocalDate bookedDate : master.getCalendar().getBookedDates().keySet()) {
                bufferedWriter.append(( bookedDate ) + "," );
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
}
