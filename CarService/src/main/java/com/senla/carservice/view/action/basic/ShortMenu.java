package com.senla.carservice.view.action.basic;

import com.senla.carservice.view.action.IAction;

public class ShortMenu implements IAction {
    @Override
    public void execute() {
        System.out.print("Print '0' if you want to exit Application   ");
        System.out.println("Print '1' if you want to back to the Root menu");
        System.out.println("____________________________________________________________________________________________");
    }
}
