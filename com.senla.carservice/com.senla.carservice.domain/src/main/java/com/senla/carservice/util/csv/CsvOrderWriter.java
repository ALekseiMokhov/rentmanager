package com.senla.carservice.util.csv;


import com.senla.carservice.entity.master.AbstractMaster;
import com.senla.carservice.entity.order.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CsvOrderWriter {

    private static final File FILE = new File( "./files/order.csv" );

    public static void writeOrder(Order order) throws IOException {
        removeOldMaster( order.getId() );

        try (BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( FILE, true ) )) {
            bufferedWriter.append( "\n" );
            bufferedWriter.append( order.getId().toString() + "," );
            bufferedWriter.append( order.getDateBooked() + "," );
            bufferedWriter.append( order.getStartOfExecution() + "," );
            bufferedWriter.append( order.getPlace().getId() + "," );
            for (AbstractMaster master : order.getMasters()) {
                bufferedWriter.append( master.getId() + "," );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        CsvPlaceWriter.writePlace( order.getPlace() );
        CsvMasterWriter.writeMasters( order.getMasters() );

    }

    public static void writeOrders(List <Order> orders) throws IOException {

        for (Order order : orders) {
            writeOrder( order );
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
