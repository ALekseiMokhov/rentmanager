package property.configurer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class PropertyLoader {

    @SneakyThrows
    public static void loadDefaultProperties() {

        try {
            InputStream inputStream = new FileInputStream( "config.properties" );
            PropertyStorage.getCachedProperties().load( inputStream );
        } catch (IOException e) {
            log.error( e + e.getMessage() );
        }
    }

    public static void loadCustomProperties(String file) {

        try {
            InputStream inputStream = PropertyLoader.class.getResourceAsStream( file );
            PropertyStorage.getCachedProperties().load( inputStream );
        } catch (IOException e) {
            log.error( e + e.getMessage() );
        }
    }


}
