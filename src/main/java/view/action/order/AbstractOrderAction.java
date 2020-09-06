package view.action.order;

import util.scanner.ConsoleScanner;
import view.action.IAction;

import java.io.BufferedReader;

public abstract class AbstractOrderAction implements IAction {
    protected BufferedReader reader = ConsoleScanner.getInstance().getReader();
}
