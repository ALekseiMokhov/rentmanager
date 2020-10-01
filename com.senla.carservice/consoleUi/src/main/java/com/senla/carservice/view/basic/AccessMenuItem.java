package com.senla.carservice.view.basic;


import com.senla.carservice.view.action.IAction;

public class AccessMenuItem implements IAction {
    @Override
    public void execute() {
        System.out.println( "You've got no permission to modify data!" );
        System.out.println( "Print 1 to go back to the root menu" );
        System.out.println( "Print 2 to exit Application" );
    }
}
