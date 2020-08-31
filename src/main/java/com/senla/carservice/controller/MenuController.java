package com.senla.carservice.controller;


import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

import properties.configurer.PropertyInjector;
import view.menu.Builder;
import view.menu.Navigator;

import java.io.IOException;

@Controller
public class MenuController implements IController, ApplicationContextAware {
    private ApplicationContext context;
    private Navigator navigator;
    @Getter
    private Builder builder;
    private PropertyInjector propertyInjector;

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


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       this.context = applicationContext;
    }
}


