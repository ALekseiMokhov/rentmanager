package com.senla.carservice.view.action.order;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class ShiftDateAction extends AbstractOrderAction {
    private UUID id;
    private LocalDate startOfExecution;

    @Override
    public void execute() {
        System.out.println( "Enter the id of the order: " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "UUID should have proper format" );
        }


        try {

            System.out.println( "Enter the date of execution: " );
            startOfExecution = LocalDate.parse( reader.readLine() );

            controller.shiftOrderExecutionDate( id, startOfExecution );

            System.out.println( "The date of execution has been shifted successfully!" );
            System.out.println( "The new date is " + startOfExecution );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "The Date should have proper format!" );
        }


    }
}
