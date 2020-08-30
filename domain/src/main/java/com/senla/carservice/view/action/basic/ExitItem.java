package com.senla.carservice.view.action.basic;

import com.senla.carservice.view.action.IAction;

public class ExitItem implements IAction {


    @Override
    public void execute() {


        System.out.println( "Goodbye visitor! Hope to see you again!" );
    }
}
