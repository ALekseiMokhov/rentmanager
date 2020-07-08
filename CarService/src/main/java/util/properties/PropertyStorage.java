package util.properties;

import java.util.Properties;

public class PropertyStorage {


    private final static Properties CACHED_PROPERTIES = new Properties();


    public static void load(String key,String value)  {
          CACHED_PROPERTIES.put( key,value );
    }
    public static String get(String key)  {
        return  CACHED_PROPERTIES.getProperty( key);
    }

    public static Properties getCachedProperties() {
        return CACHED_PROPERTIES;
    }
}
