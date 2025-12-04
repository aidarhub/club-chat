package my.important.job.util;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();
    public static final String DB_USERNAME = "db.username";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_URL = "db.url";
    public static final String POOL_SIZE = "pool.size";

    private PropertiesUtil() { }

    static {
        try (var props = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(props);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<String> get(String key) {
        return Optional.ofNullable(PROPERTIES.getProperty(key));
    }

    public static String getOrElse(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue);
    }
}
