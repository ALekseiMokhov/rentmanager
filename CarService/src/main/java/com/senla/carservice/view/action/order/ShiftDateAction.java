package com.senla.carservice.view.action.order;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class ShiftDateAction extends AbstractOrderAction {
    @Override
    public void execute() {
        System.out.println( "Enter the id of the order: " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "Enter the date of execution: " );

        try {
            startOfExecution = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller.shiftOrderExecutionDate( controller.findOrderById( id ), startOfExecution );
        System.out.println( "The date of execution has been shifted successfully!" );
        System.out.println( "The new date is " + startOfExecution );

    }
}
