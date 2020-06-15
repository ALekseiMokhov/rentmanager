package com.senla.carservice.view.action.place;

import util.Scanner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class IsPlaceSetAction extends AbstractPlaceAction {
    @Override
    public void execute() {
        System.out.println("Enter UUID of place to check: ");
        try {
          id = UUID.fromString(Scanner.getInstance().getReader().readLine());
        }
        catch (IOException e)  {

        }
        System.out.println("Enter the Date to check: ");

        try {
            date = LocalDate.parse(Scanner.getInstance().getReader().readLine()  );
        }
        catch (IOException e)  {

        }

        boolean isFree = controller.isPlaceSetForDate( controller.getPlaceById( id ),date );
        System.out.println(" The place with id "+ id + " is free: " + isFree );
    }
}
