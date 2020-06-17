package com.senla.carservice.view.action.basic;

import com.senla.carservice.view.action.IAction;

public class RootMenuItem implements IAction {
    @Override
    public void execute() {
        System.out.println("Greetings, visitor!");
        System.out.println("    ________  ");
        System.out.println("   /        \\ ");
        System.out.println(" [[----------]] ");
        System.out.println("+[-(*)----(*)-]+ ");
        System.out.println(" --------------              ");

        System.out.println("Print '1' if you want to get Garage data;");
        System.out.println("Print '2' if you want to get Master data;");
        System.out.println("Print '3' if you want to book order or correct any data;");
        System.out.println("Print '4' to exit Application");

    }
}
