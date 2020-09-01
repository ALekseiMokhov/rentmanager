package com.senla.carservice.view.master;

import com.senla.carservice.controller.MasterController;

import com.senla.carservice.entity.master.IMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class GetMasterByIdAction extends AbstractMasterAction {
    @Autowired
    MasterController controller;
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
            log.error( "COULD NOT FIND MASTER BY ID! CAUSE: " + e.getStackTrace() );
        }

    }
}
