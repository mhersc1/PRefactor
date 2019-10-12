package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
    public static Properties loadProperties(String configFile) {
        try {
            Properties connectionProps = new Properties();
            InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(configFile);
            connectionProps.load(inputStream);
            return connectionProps;
        } catch (IOException e) {
            throw new RuntimeException("It happened an error at loading properties file: " + configFile);
        }
    }
}
