package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.IMaster;

import java.io.IOException;
import java.util.UUID;

public class GetMasterByIdAction extends AbstractMasterAction {
    private IMaster master;
    private UUID id;

    @Override
    public void execute() {

        try {
            System.out.println( "Enter the id of master : " );
            id = UUID.fromString( reader.readLine() );

            master = controller.getById( id );
            System.out.println( "Your master is: " + master );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "UUID should have proper format!" );
        }

    }
}
