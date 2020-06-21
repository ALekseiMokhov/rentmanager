package com.senla.carservice.view.action.basic;

import com.senla.carservice.view.action.IAction;

public class MasterMenuItem implements IAction {
    @Override
    public void execute() {
        System.out.println(" |-*****************Master Business Logic**************************" );
        System.out.println(" |-print '1' to add a new Master");
        System.out.println(" |-print '2' to book the Master for the Date" );
        System.out.println(" |-print '3' to unbook the Master for the Date ");
        System.out.println(" |-print '4' to check whether the Master booked for the Date" );
        System.out.println(" |-print '5' to delete Master from DB" );
        System.out.println(" |-print '6' to find Master by his name and speciality" );
        System.out.println(" |-print '7' to find a free Master of chosen speciality for the Date" );
        System.out.println(" |-print '8' to find all free Master for the Date" );
        System.out.println(" |-print '9' to find Master by id" );
        System.out.println(" |-print '10' to find all Master sorted by alphabet" );
        System.out.println(" |-print '11' to find all Master of chosen speciality" );
        System.out.println(" |-print '12' to find all available specialities" );
        System.out.println(" |-print '13' if you want to exit Application" );
    }
}
