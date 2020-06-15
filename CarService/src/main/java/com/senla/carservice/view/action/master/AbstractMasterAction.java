package com.senla.carservice.view.action.master;

import com.senla.carservice.controller.MasterController;
import com.senla.carservice.domain.repository.MasterRepository;
import com.senla.carservice.domain.service.MasterService;
import com.senla.carservice.view.action.IAction;

public abstract class AbstractMasterAction implements IAction {
    MasterController controller = new MasterController( new MasterService( new MasterRepository() ) ) ;
}
