package com.senla.carservice.view.action.master;

import java.io.IOException;
import java.util.UUID;

public class GetMasterByIdAction extends AbstractMasterAction {
    @Override
    public void execute() {
        System.out.println( "Enter the id of master : " );
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        master = controller.getById( id );
        System.out.println( "Your master is: " + master );
    }
}
