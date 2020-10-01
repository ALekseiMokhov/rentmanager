package com.senla.carservice.view.master;

import com.senla.carservice.controller.MasterController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;

public class RemoveMasterAction extends AbstractMasterAction {
    @Autowired
    MasterController controller;
    private UUID id;

    @Override
    public void execute() {
        try {
            System.out.println( "Enter the id of master you want to delete: " );
            id = UUID.fromString( reader.readLine() );

            controller.removeMaster( id );
            System.out.println( "Master with id " + id + " was successfully removed!" );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "UUID should have proper format!" );
        }

    }
}
