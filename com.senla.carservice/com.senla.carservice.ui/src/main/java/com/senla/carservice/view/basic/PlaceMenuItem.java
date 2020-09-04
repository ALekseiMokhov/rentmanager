package com.senla.carservice.view.basic;


import com.senla.carservice.view.action.IAction;

public class PlaceMenuItem implements IAction {
    @Override
    public void execute() {
        System.out.println( " |-*****************Place Business Logic**************************" );
        System.out.println( " |-print '1' to add places to the Garage" );
        System.out.println( " |-print '2' to show all places in garage" );
        System.out.println( " |-print '3' to find a free place for the Date" );
        System.out.println( " |-print '4' to find all free places for the Date" );
        System.out.println( " |-print '5' to check whether the Place booked for the Date" );
        System.out.println( " |-print '6' to save the Place to DB" );
        System.out.println( " |-print '7' to book the Place for the Date" );
        System.out.println( " |-print '8' to unbook the Place for the Date" );
        System.out.println( " |-print '9' to set custom id for the Place" );
        System.out.println( " |-print '10' to load all places from csv file" );
        System.out.println( " |-print '11' to write all places to csv file" );
        System.out.println( " |-print '12' to delete Place by id" );
        System.out.println( " |-print '13' to go back to the RootMenu" );
        System.out.println( " |-Print '14' if you want to exit Application   " );

    }
}
