package com.senla.carservice.view.action.order;

import com.senla.carservice.controller.OrderController;
import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.master.Speciality;
import com.senla.carservice.domain.entities.order.Order;
import com.senla.carservice.domain.entities.order.OrderStatus;
import com.senla.carservice.domain.repository.MasterRepository;
import com.senla.carservice.domain.repository.OrderRepository;
import com.senla.carservice.domain.repository.PlaceRepository;
import com.senla.carservice.domain.service.MasterService;
import com.senla.carservice.domain.service.OrderService;
import com.senla.carservice.domain.service.PlaceService;
import com.senla.carservice.view.action.IAction;
import util.Scanner;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public abstract class AbstractOrderAction implements IAction {

    OrderController controller =
            new OrderController( new OrderService( new OrderRepository(),
                    new MasterService( new MasterRepository() ),
                    new PlaceService( new PlaceRepository() ) ) );
    Order order;

    OrderStatus status;

    LocalDate dateBooked;

    LocalDate startOfExecution;

    LocalDate finishOfExecution;

    List <IMaster> masters;

    Place place;

    UUID id;

    BufferedReader reader = Scanner.getInstance().getReader();

    Set <OrderStatus> possibleStatus = Set.of( OrderStatus.values() );

    Set <Speciality> required;

    Set <Speciality> availableSpecialities = Set.of( Speciality.values() );

}
