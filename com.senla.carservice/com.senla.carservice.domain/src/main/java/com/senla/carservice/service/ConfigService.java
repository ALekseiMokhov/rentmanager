package com.senla.carservice.service;

import com.senla.carservice.properties.configurer.PropertyLoader;
import com.senla.carservice.properties.configurer.PropertyStorage;
import org.springframework.stereotype.Service;


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
