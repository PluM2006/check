package ru.clevertec.app.controller.listener;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import ru.clevertec.app.connection.ConnectionPool;

import java.sql.Connection;

@WebListener
public class ServletListener implements ServletContextListener {

    public static final String PATH_CHANGE_XML = "changeLog/changelog.xml";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
             Liquibase liquibase = new Liquibase(PATH_CHANGE_XML, new ClassLoaderResourceAccessor(), database)){
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroyPool();
    }

    // implementations for other methods
}
