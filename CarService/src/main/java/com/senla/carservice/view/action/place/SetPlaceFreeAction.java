package com.senla.carservice.view.action.place;

import util.Scanner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class SetPlaceFreeAction extends AbstractPlaceAction {
    @Override
    public void execute() {
        System.out.println(" Enter the id of booked place: ");
        try {
            id = UUID.fromString( Scanner.getInstance().getReader().readLine());
        }
        catch (IOException e)  {

        }
        System.out.println("Enter the Date to unbook place: ");
        try {
            date = LocalDate.parse(Scanner.getInstance().getReader().readLine()  );
        }
        catch (IOException e)  {

        }
        this.controller.setPlaceFree( this.controller.getPlaceById( id ),date );
        System.out.println("The place was unbooked successfully!");
    }
}
