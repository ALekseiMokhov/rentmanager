package com.senla.carservice.properties.configurer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class PropertyLoader {

    @SneakyThrows
    public static void loadDefaultProperties() {

        try (InputStream inputStream = new FileInputStream( "application.properties" )) {
            PropertyStorage.getCachedProperties().load( inputStream );
        } catch (IOException e) {
            log.error( e + " Properties were not loaded!" );

        }
    }

    public static void loadCustomProperties(String file) {
        try (InputStream inputStream = PropertyLoader.class.getResourceAsStream( file )) {
            PropertyStorage.getCachedProperties().load( inputStream );
        } catch (IOException e) {
            log.error( e + " Custom properties were not loaded!" );
        }
    }


}
