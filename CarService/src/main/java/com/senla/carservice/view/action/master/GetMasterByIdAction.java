package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class GetMasterByIdAction extends AbstractMasterAction {
    private IMaster master;
    private UUID id;

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
