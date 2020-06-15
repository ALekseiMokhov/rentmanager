package com.senla.carservice.view.action.place;

import com.senla.carservice.domain.entities.garage.Place;
import util.Scanner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class GetFreePlacesAction extends AbstractPlaceAction{

    @Override
    public void execute() {
        
        System.out.println("Enter convinient Date: ");
        try {
            date = LocalDate.parse(Scanner.getInstance().getReader().readLine()  );
        }
        catch (IOException e)  {

        }
        System.out.println(
               "Available places are: "
        );
        List <Place> placeList = this.controller.getFreePlacesForDate(date  );
        for (Place val : placeList) {
            System.out.println(val);
        }
    }
}
