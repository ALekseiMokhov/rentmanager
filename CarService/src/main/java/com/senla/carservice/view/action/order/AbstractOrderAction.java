package com.senla.carservice.view.action.order;

import com.senla.carservice.controller.OrderController;
import com.senla.carservice.domain.repository.MasterRepository;
import com.senla.carservice.domain.repository.OrderRepository;
import com.senla.carservice.domain.repository.PlaceRepository;
import com.senla.carservice.domain.service.MasterService;
import com.senla.carservice.domain.service.OrderService;
import com.senla.carservice.domain.service.PlaceService;
import com.senla.carservice.view.action.IAction;

public abstract class AbstractOrderAction implements IAction {

        OrderController controller =
                new OrderController( new OrderService(new OrderRepository(),
                        new MasterService(new MasterRepository() ),
                        new PlaceService( new PlaceRepository() ) ) )  ;

}
