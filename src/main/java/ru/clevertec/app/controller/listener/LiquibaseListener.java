package ru.clevertec.app.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import liquibase.integration.servlet.LiquibaseJakartaServletListener;

@WebListener
public class LiquibaseListener extends LiquibaseJakartaServletListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        super.contextDestroyed(sce);
    }
}
