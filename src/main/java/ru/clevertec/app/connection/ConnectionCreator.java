package ru.clevertec.app.connection;

import ru.clevertec.app.utils.YamlUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionCreator {

    private static final String DATABASE_URL = YamlUtils.getYamlProperties().getDatabase().getUrl();
    private static final String DATABASE_USER =  YamlUtils.getYamlProperties().getDatabase().getUser();
    private static final String DATABASE_PASSWORD = YamlUtils.getYamlProperties().getDatabase().getPassword();

    public static ProxyConnection createConnection(){
        Connection connection;
        ProxyConnection proxyConnection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            proxyConnection = new ProxyConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return proxyConnection;
    }
}