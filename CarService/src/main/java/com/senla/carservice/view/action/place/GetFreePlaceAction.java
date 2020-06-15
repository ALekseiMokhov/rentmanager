package com.senla.carservice.view.action.place;

import com.senla.carservice.domain.entities.garage.Place;
import util.Scanner;

import java.io.IOException;
import java.time.LocalDate;

public class GetFreePlaceAction extends AbstractPlaceAction{
    @Override
    public void execute() {
        System.out.println("Enter convinient Date: ");
        try {
            date = LocalDate.parse(Scanner.getInstance().getReader().readLine()  );
        }
        catch (IOException e)  {

        }
        System.out.println(
                "Available place is: "
        );
        Place place = this.controller.getFreePlace(date );
        System.out.println(place);

    }
}
