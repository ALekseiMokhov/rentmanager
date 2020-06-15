package com.senla.carservice.view.action.basic;

import com.senla.carservice.view.action.IAction;

public class Greeting implements IAction {
    @Override
    public void execute() {
        System.out.println("Greetings, visitor!");
        System.out.println("    ________  ");
        System.out.println("   /        \\ ");
        System.out.println(" [[----------]] ");
        System.out.println("+[-(*)----(*)-]+ ");
        System.out.println(" --------------              ");

        System.out.println(" |-print '2' to add places to the Garage");
        System.out.println(" |-print '3' to add a new Master");
        System.out.println(" |-print '4' to add a new Order" );
        System.out.println(" |-*****************Place Business Logic**************************" );
        System.out.println(" |-print '5' to show all places in garage" );
        System.out.println(" |-print '6' to find a free place for the Date" );
        System.out.println(" |-print '7' to find all free places for the Date" );
        System.out.println(" |-print '8' to check whether the Place booked for the Date" );
        System.out.println(" |-print '9' to save the Place to DB" );
        System.out.println(" |-print '10' to book the Place for the Date" );
        System.out.println(" |-print '11' to unbook the Place for the Date" );
        System.out.println(" |-print '12' to set custom id for the Place" );
        System.out.println(" |-*****************Master Business Logic**************************" );
        System.out.println(" |-print '13' to book the Master for the Date" );
        System.out.println(" |-print '14' to unbook the Master for the Date ");
        System.out.println(" |-print '15' to check whether the Master booked for the Date" );
        System.out.println(" |-print '16' to delete Master from DB" );
        System.out.println(" |-print '17' to find Master by his name and speciality" );
        System.out.println(" |-print '18' to find a free Master of chosen speciality for the Date" );
        System.out.println(" |-print '19' to find all free Master for the Date" );
        System.out.println(" |-print '20' to find Master by id" );
        System.out.println(" |-print '21' to find all Master sorted by alphabet" );
        System.out.println(" |-print '22' to find all Master of chosen speciality" );
        System.out.println(" |-print '23' to find all available specialities" );
        System.out.println(" |-******************Order Business Logic***************************" );
        System.out.println(" |-print '24' to cancel chosen Order" );
        System.out.println(" |-print '25' to complete chosen Order" );
        System.out.println(" |-print '26' to find order by id" );
        System.out.println(" |-print '27' to find all orders" );
        System.out.println(" |-print '28' to find all orders of chosen status filtered by booking date" );
        System.out.println(" |-print '29' to find all executed orders of chosen date" );
        System.out.println(" |-print '30' to find all orders for specific period" );
        System.out.println(" |-print '31' to set new masters for the order" );
        System.out.println(" |-print '32' to move on date of execution of the chosen order" );

    }
}
