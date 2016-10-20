package shared;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class AppProperties {

    private static Properties properties;

    private AppProperties(){

    }
    public static void load() {
        try {
            properties.load(new FileInputStream("resources/application.properties"));
        } catch (IOException e) {
            properties = new Properties();
        }
    }

    public static String getProperty(String name, String defaultValue) {
        return getPropertyWithPrefix(name, defaultValue);
    }

    public static String getProperty(String name) {
        final String propertyNamePrefixed = "application." + name;
        return getPropertyWithPrefix(propertyNamePrefixed);
    }

    public static String getPropertyWithPrefix(String nameWithPrefix) {
        Object property = properties.get(nameWithPrefix);
        return property == null ? "" : property.toString();
    }

    public static String getPropertyWithPrefix(String nameWithPrefix, String defaultValue) {
        String property = getPropertyWithPrefix(nameWithPrefix);
        if(property == null || property.isEmpty()) {
            property = defaultValue;
        }
        return property;
    }
}
