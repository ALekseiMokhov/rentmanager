package com.senla.carservice.view.menu;


import com.senla.carservice.properties.configurer.PropertyInjector;
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

    public void run() throws IOException {

        builder = new Builder();
        PropertyInjector.injectProperty( builder );
        builder.setApplicationContext( this.context );


        navigator = new Navigator(
                builder.buildRootMenu()
                , builder.buildPlaceMenu()
                , builder.buildMasterMenu()
                , builder.buildOrderMenu()
                , builder.buildAccessMenu() );
        navigator.navigate( navigator.getRootMenu() );
    }


}


