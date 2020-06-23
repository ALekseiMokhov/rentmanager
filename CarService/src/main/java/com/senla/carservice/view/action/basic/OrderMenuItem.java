package com.senla.carservice.view.action.basic;

import com.senla.carservice.view.action.IAction;

public class OrderMenuItem implements IAction {
    @Override
    public void execute() {
        System.out.println(" |-******************Order Business Logic***************************" );
        System.out.println(" |-print '1' to add a new Order" );
        System.out.println(" |-print '2' to cancel chosen Order" );
        System.out.println(" |-print '3' to complete chosen Order" );
        System.out.println(" |-print '4' to find order by id" );
        System.out.println(" |-print '5' to find all orders" );
        System.out.println(" |-print '6' to find all orders of chosen status filtered by booking date" );
        System.out.println(" |-print '7' to find all executed orders of chosen date" );
        System.out.println(" |-print '8' to find all orders for specific period" );
        System.out.println(" |-print '9' to set new masters for the order" );
        System.out.println(" |-print '10' to move on date of execution of the chosen order" );
        System.out.println(" |-print '11' to write all orders to csv file" );
        System.out.println(" |-print '12' to load all orders from csv file" );
        System.out.println(" |-print '13' to go back to the RootMenu" );
        System.out.println(" |-print '14' if you want to exit Application" );
    }
}
