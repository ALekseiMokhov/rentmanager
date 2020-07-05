package com.senla.carservice.view.action.basic;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.controller.OrderController;
import com.senla.carservice.controller.PlaceController;
import com.senla.carservice.view.action.IAction;

public class ExitItem implements IAction {
    private PlaceController placeController = new PlaceController();
    private MasterController masterController = new MasterController();
    private OrderController orderController = new OrderController();
    @Override
    public void execute() {

        placeController.exportToJson();
        masterController.exportToJson();
        orderController.exportToJson();

        System.out.println( "Goodbye visitor! Hope to see you again!" );
    }
}
