package view.action.basic;

import view.action.IAction;

public class ExitItem implements IAction {


    @Override
    public void execute() {


        System.out.println( "Goodbye visitor! Hope to see you again!" );
    }
}
