package com.senla.carservice.view.action.basic;

import com.senla.carservice.view.action.IAction;

public class Greeting implements IAction {
    @Override
    public void execute() {
        System.out.println("Greeting, visitor!");
        System.out.println("    ________  ");
        System.out.println("   /        \\ ");
        System.out.println(" [[----------]] ");
        System.out.println("+[-(*)----(*)-]+ ");
        System.out.println(" --------------              ");

        System.out.println("Print '2' if you want to add new order,");
        System.out.println("Print '3' if you want to correct an existing order,");
        System.out.println("Print '4' if you want to require information...");
        System.out.println("Print '0' if you want to exit Application.");
    }
}
