package com.senla.carservice.view.menu;


import com.senla.carservice.spring.config.PropertyInitializer;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MenuController {
    @Autowired
    private ApplicationContext context;
    private Navigator navigator;
    @Getter
    private Builder builder;

    public MenuController(ApplicationContext context) {
        this.context = context;
    }

    public void run() throws IOException {

        builder = new Builder(this.context);
        PropertyInitializer initializer = (PropertyInitializer)this.context.getBean( "propertyInitializer" );
        builder.setIsGarageModificationPermitted( initializer.getIsGarageModificationPermitted());
        builder.setIsMasterModificationPermitted( initializer.getIsMasterModificationPermitted() );
        builder.setIsOrderModificationPermitted( initializer.getIsOrderModificationPermitted() );
        builder.setIsJsonPersistenceAllowed( initializer.getIsJsonPersistenceAllowed() );


        navigator = new Navigator(
                builder.buildRootMenu()
                , builder.buildPlaceMenu()
                , builder.buildMasterMenu()
                , builder.buildOrderMenu()
                , builder.buildAccessMenu() );
        navigator.navigate( navigator.getRootMenu() );
    }


}


