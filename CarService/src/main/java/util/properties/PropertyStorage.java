package util.properties;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    private final static Properties DEFAULT_PROPERTIES = new Properties();
    private final static Properties CACHED_Properties = new Properties();
    static String rootPath = "";

    static {
        rootPath = Thread.currentThread().getContextClassLoader().getResource( "" ).getPath() + "config.properties";
        try {
            DEFAULT_PROPERTIES.load( new FileInputStream( rootPath ) );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
