package com.senla.carservice.view.action.order;

import com.senla.carservice.domain.entities.master.Speciality;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AddOrderAction extends AbstractOrderAction {
    @Override
    public void execute() {
        System.out.println( "Enter specialities required for your Order using ',' as delimiter " );
        System.out.println( "(available are: " );
        System.out.println("____________");
        availableSpecialities.stream().forEach( System.out::println);
        System.out.print( " ):" );

        try {
            List <String> input = List.of( reader.readLine().split( "," ));
            required = input.stream()
                    .map( s -> Speciality.valueOf( s.toUpperCase() ) )
                    .collect( Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Enter the date of execution: ");

        try {
            startOfExecution = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller.addOrder( LocalDate.now(),startOfExecution,required );
    }
}
