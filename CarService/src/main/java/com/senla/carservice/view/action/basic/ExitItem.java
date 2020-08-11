package com.senla.carservice.view.action.basic;

import com.senla.carservice.controller.JsonController;
import com.senla.carservice.view.action.IAction;
import dependency.injection.beanfactory.BeanFactory;
import property.configurer.annotations.ConfigProperty;

public class ExitItem implements IAction {
    @ConfigProperty(propertyName = "json.persistence.mode")
    private Boolean isJsonPersistenceAllowed;
    @Override
    public void execute() {
        if (isJsonPersistenceAllowed) {
            JsonController jsonController =
                    (JsonController) BeanFactory.getInstance().getSingleton( "jsoncontroller" );
            jsonController.exportToJson();
        }
        System.out.println( "Goodbye visitor! Hope to see you again!" );
    }
}
