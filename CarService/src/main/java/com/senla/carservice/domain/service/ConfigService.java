package com.senla.carservice.domain.service;

import util.properties.PropertyLoader;
import util.properties.PropertyStorage;

import java.io.IOException;

public class ConfigService implements IConfigService {



    public ConfigService() {
    }



    @Override
    public void loadCustomProps(String propName) {
         PropertyLoader.loadCustomProperties( propName );
    }

    @Override
    public void loadDefaultProps() {

            PropertyLoader.loadDefaultProperties();

    }

    @Override
    public String get(String s) {
       return PropertyStorage.get( s );
    }

    @Override
    public void write(String k, String v) {
        PropertyStorage.load( k, v );
    }


}
