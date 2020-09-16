package com.senla.carservice.view.master;

import com.senla.carservice.controller.MasterController;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class DeleteMasterAction extends AbstractMasterAction {

    @Autowired
    MasterController controller;

    @Override
    @SneakyThrows
    public void execute() {

        System.out.println( "Enter id of Master to delete: " );
        UUID id = UUID.fromString( reader.readLine() );
        this.controller.deleteMaster( id );
    }
}
