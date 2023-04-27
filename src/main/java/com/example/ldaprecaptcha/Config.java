package com.example.ldaprecaptcha;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final String PROPERTIES_FILE = "/application.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = Config.class.getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}