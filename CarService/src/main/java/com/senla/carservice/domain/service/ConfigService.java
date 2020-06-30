package com.senla.carservice.domain.service;

import util.properties.PropertyUtil;

import java.io.IOException;

public class ConfigService implements IConfigService {

    private static ConfigService instance;

    private ConfigService() {
    }

    public static ConfigService getInstance() {
        if (instance == null) {
            instance = new ConfigService();
        }
        return instance;
    }

    @Override
    public void readAll() {
        try {
            PropertyUtil.readAll();
        } catch (IOException e) {
            System.err.println( "Property file wasn't found!" );
        }
    }

    @Override
    public void read(String s) {
        PropertyUtil.printProperty( s );
    }

    @Override
    public void write(String k, String v) {
        try {
            PropertyUtil.writeProperty( k, v );
        } catch (IOException e) {
            System.err.println( "Property file wasn't found!" );

        }
    }

    @Override
    public String get(String s) {
        return PropertyUtil.getPropertyValue( s );
    }
}
