package ru.clevertec.app.service.connection;

import ru.clevertec.app.service.utils.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static ConnectionPool instants;
    private final BlockingDeque<ProxyConnection> freeConnections;
    private final BlockingDeque<ProxyConnection> activeConnections;

    private final String DATABASE_DRIVER = PropertiesUtil.getDriver();

    private ConnectionPool() {
        int DEFAULT_POLL_SIZE = Integer.parseInt(PropertiesUtil.getPoolSize());
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POLL_SIZE);
        activeConnections = new LinkedBlockingDeque<>(DEFAULT_POLL_SIZE);
        registerDriver();
        for (int i = 0; i< DEFAULT_POLL_SIZE; i++){
            freeConnections.offer(ConnectionCreator.createConnection());
        }
    }
    public static ConnectionPool getInstance(){
        if (instants ==null){
            instants = new ConnectionPool();
        }
        return instants;
    }

    public Connection getConnection(){
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            activeConnections.push(connection);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return connection;
    }
    public void releaseConnection(Connection connection){
        if (connection instanceof ProxyConnection &&
                activeConnections.remove(connection)){
            try {
                if (!connection.getAutoCommit()){
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            freeConnections.offer((ProxyConnection) connection);
        } else {
            System.err.println("Error");
        }
    }
    private void registerDriver(){
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void deregisterDriver(){
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
