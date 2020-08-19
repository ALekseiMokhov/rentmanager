package property.configurer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PropertyLoader {
    static String rootPath = "";

    public static void loadDefaultProperties() {
        rootPath = Thread.currentThread().getContextClassLoader().getResource( "" ).getPath() + "config.properties";
        try {
            PropertyStorage.getCachedProperties().load( new FileInputStream( rootPath ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadCustomProperties(String file) {

        try {
            InputStream inputStream = PropertyLoader.class.getResourceAsStream( file )  ;
            PropertyStorage.getCachedProperties().load( inputStream );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
