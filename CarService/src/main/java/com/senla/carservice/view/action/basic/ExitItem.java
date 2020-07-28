package com.senla.carservice.view.action.basic;

import com.senla.carservice.controller.JsonController;
import com.senla.carservice.view.action.IAction;
import dependency.injection.beanfactory.BeanFactory;

public class ExitItem implements IAction {

    @Override
    public void execute() {
        JsonController jsonController =
                (JsonController) BeanFactory.getInstance().getSingleton( "jsoncontroller" );
        jsonController.exportToJson();
        System.out.println( "Goodbye visitor! Hope to see you again!" );
    }
}
