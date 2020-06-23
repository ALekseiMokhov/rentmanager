package util.csv;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.order.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CsvOrderWriter {
    public static void writeOrder (Order order) throws IOException {

        File file = new File( "./files/order.csv" );

        try (BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( file ,true) )) {
            bufferedWriter.append( "\n" );
            bufferedWriter.append(order.getId().toString()+ "," ) ;
            bufferedWriter.append( order.getDateBooked() + "," );
            bufferedWriter.append( order.getStartOfExecution()+ "," );
            bufferedWriter.append(order.getPlace().getId() + "," );
            for (IMaster master : order.getMasters()) {
                bufferedWriter.append( master.getId()+ "," );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeOrders(List <Order> orders) throws IOException {

        for (Order order : orders) {
           writeOrder( order );
        }

    }
}
