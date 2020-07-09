package property.configurer;

import java.io.FileInputStream;
import java.io.IOException;

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

    public static void loadCustomProperties(String propsName) {
        rootPath = Thread.currentThread().getContextClassLoader().getResource( "" ).getPath() + propsName;
        try {
            PropertyStorage.getCachedProperties().load( new FileInputStream( rootPath ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
