package com.senla.carservice.view.action.master;

import java.io.IOException;
import java.time.LocalDate;

public class GetFreeMastersAction extends AbstractMasterAction {
    @Override
    public void execute() {

        System.out.println( "Enter the date : " );
        try {
            date = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {

        }
        this.controller.getFreeMasters( date )
                .stream()
                .forEach( System.out::println );
    }
}
