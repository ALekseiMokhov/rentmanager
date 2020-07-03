package com.senla.carservice.view.action.master;

import java.io.IOException;
import java.time.LocalDate;

public class GetFreeMastersAction extends AbstractMasterAction {

    private LocalDate date;

    @Override
    public void execute() {
        try {
            System.out.println( "Enter the date : " );
            date = LocalDate.parse( reader.readLine() );

            this.controller.getFreeMasters( date ).stream()
                    .forEach( System.out::println );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "The Date should have proper format 'YYYY-MM-DD'" );
        }

    }
}
