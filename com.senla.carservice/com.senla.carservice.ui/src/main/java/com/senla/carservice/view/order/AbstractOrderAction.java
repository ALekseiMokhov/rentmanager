package com.senla.carservice.view.order;



import com.senla.carservice.view.action.IAction;

import java.io.BufferedReader;

public abstract class AbstractOrderAction implements IAction {
    protected BufferedReader reader = com.senla.carservice.scanner.ConsoleScanner.getInstance().getReader();
}
