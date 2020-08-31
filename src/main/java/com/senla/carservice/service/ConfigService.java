package com.senla.carservice.service;

import org.springframework.stereotype.Service;
import properties.configurer.PropertyLoader;
import properties.configurer.PropertyStorage;


@Service
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
