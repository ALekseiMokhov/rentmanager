package com.senla.carservice.view.action.order;

import com.senla.carservice.domain.entities.master.Speciality;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AddOrderAction extends AbstractOrderAction {
    private Set <Speciality> availableSpecialities = Set.of( Speciality.values() );
    private Set <Speciality> required;
    private LocalDate startOfExecution;


    @Override
    public void execute() {
        System.out.println( "Enter specialities required for your Order using ',' as delimiter " );
        System.out.println( "(available are: " );
        System.out.println( "____________" );
        availableSpecialities.stream().forEach( System.out::println );
        System.out.print( " ):" );

        try {
            List <String> input = List.of( reader.readLine().split( "," ) );
            required = input.stream()
                    .map( s -> Speciality.valueOf( s ) )
                    .collect( Collectors.toSet() );
        } catch (IOException e) {

        }


        try {
            System.out.println( "Enter the date of execution: " );
            startOfExecution = LocalDate.parse( reader.readLine() );
            controller.addOrder( LocalDate.now(), startOfExecution, required );
            System.out.println( "Order was created, the date is: " + startOfExecution );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "The Date should have format like 'YYYY-MM-DD'" );
        }


    }
}
