package ru.clevertec.app.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionCreator {

    private static final String DATABASE_URL = DBPropertiesUtil.getUrl();
    private static final String DATABASE_USER =  DBPropertiesUtil.getUser();
    private static final String DATABASE_PASSWORD = DBPropertiesUtil.getPassword();

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
