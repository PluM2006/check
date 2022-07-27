package ru.clevertec.app.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static final Properties PROPERTIES = new Properties();
    private static final String PATH_FILE = "application.properties";

    private static final String DRIVER = "database.driver";
    private static final String URL = "database.url";
    private static final String USER = "database.user";
    private static final String PASSWORD = "database.password";
    private static final String POOL_SIZE = "poll.size";


    static {
        try(InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PATH_FILE)){
            PROPERTIES.load(inputStream);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }

    public static String getDriver(){
        return PROPERTIES.getProperty(DRIVER);
    }

    public static String getUrl(){
        return PROPERTIES.getProperty(URL);
    }
    public static String getUser(){
        return PROPERTIES.getProperty(USER);
    }
    public static String getPassword(){
        return PROPERTIES.getProperty(PASSWORD);
    }
    public static String getPoolSize(){
        return PROPERTIES.getProperty(POOL_SIZE);
    }
}
