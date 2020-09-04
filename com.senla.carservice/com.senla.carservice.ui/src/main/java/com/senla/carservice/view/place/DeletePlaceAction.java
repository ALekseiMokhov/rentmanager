package com.senla.carservice.view.place;

import com.senla.carservice.controller.PlaceController;
import com.senla.carservice.view.action.IAction;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class DeletePlaceAction implements IAction {
    @Autowired
    private PlaceController controller;

    @SneakyThrows
    @Override
    public void execute() {
        System.out.println( " Enter the old id of place: " );
        UUID id = UUID.fromString( reader.readLine() );
        this.controller.deletePlace(  id);
    }
}
