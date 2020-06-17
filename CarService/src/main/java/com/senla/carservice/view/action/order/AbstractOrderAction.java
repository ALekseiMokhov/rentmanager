package com.senla.carservice.view.action.order;

import com.senla.carservice.controller.OrderController;
import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.domain.entities.order.OrderStatus;
import com.senla.carservice.view.action.IAction;
import util.Scanner;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public abstract class AbstractOrderAction implements IAction {

    OrderController controller = new OrderController();

    BufferedReader reader = Scanner.getInstance().getReader();


}
