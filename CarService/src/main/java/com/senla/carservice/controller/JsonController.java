package com.senla.carservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class JsonController {
    @Autowired
    private OrderController orderController;
    @Autowired
    private MasterController masterController;
    @Autowired
    private PlaceController placeController;

    public void loadFromJson() {
        this.placeController.loadFromJson();
        this.masterController.loadFromJson();
        this.orderController.loadFromJson();
    }

    public void exportToJson() {
        this.placeController.exportToJson();
        this.masterController.exportToJson();
        this.orderController.exportToJson();
    }
}
