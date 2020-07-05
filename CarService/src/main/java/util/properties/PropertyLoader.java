package util.properties;

import java.io.FileInputStream;
import java.io.IOException;

public class PropertyReader {
    static String rootPath = "";

    static {
        rootPath = Thread.currentThread().getContextClassLoader().getResource( "" ).getPath() + "config.properties";
        try {
            PropertyStorage.getCachedProperties().load( new FileInputStream( rootPath ) );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
