package com.senla.carservice.controller;

import com.senla.carservice.domain.service.ConfigService;
import com.senla.carservice.domain.service.IConfigService;

import java.io.IOException;

public class ConfigController {
    private IConfigService configService = ConfigService.getInstance();

    public void readAll() {
        this.configService.readAll();
    }


    public void read(String s) {
        this.configService.read( s );
    }


    public void write(String k, String v) throws IOException {
        this.configService.write( k, v );
    }


    public String get(String s) {
        return this.configService.get( s );
    }
}
