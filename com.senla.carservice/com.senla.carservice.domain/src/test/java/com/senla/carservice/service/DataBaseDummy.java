package com.senla.carservice.service;

import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.entity.master.AbstractMaster;
import com.senla.carservice.entity.order.Order;
import lombok.Data;

import java.util.List;
@Data
public class DataBaseDummy {
    private List <Place>placeList;
    private List <AbstractMaster>masterList;
    private List <Order>orderList;

}
