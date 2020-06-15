package com.senla.carservice.view.action.master;

import java.io.IOException;
import java.util.UUID;

public class RemoveMasterAction extends AbstractMasterAction {
    @Override
    public void execute() {
        System.out.println("Enter the id of master you want to delete: ");
        try {
            id = UUID.fromString( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.removeMaster( id );
        System.out.println("Master with id " + id + " was successfully removed!");
    }
}
