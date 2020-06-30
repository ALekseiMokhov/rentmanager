package util.properties;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    private static Properties properties = new Properties();
    static String rootPath = "";

    static {
        rootPath = Thread.currentThread().getContextClassLoader().getResource( "" ).getPath() + "config.properties";
        try {
            properties.load( new FileInputStream( rootPath ) );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readAll() throws IOException {

        properties.list( System.out );
    }

    public static void printProperty(String property) {
        System.out.println( property + " = " + properties.getProperty( property ) );
    }

    public static void writeProperty(String property, String value) throws IOException {
        properties.put( property, value );
        properties.store( new FileWriter( rootPath ), "new value " + value + "stored to properties file" );
    }

    public static String getPropertyValue(String property) {

        return properties.getProperty( property, "true" );       /* */
    }
}
