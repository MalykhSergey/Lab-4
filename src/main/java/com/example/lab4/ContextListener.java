package com.example.lab4;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.SQLException;

public class ContextListener implements ServletContextListener {

    private final CarService carService;
    private final DataSource dataSource;

    public ContextListener() throws SQLException {
        dataSource = new DataSource();
        carService = new CarService(dataSource);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("carService", carService);
        servletContextEvent.getServletContext().setAttribute("dataSource", dataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        dataSource.close();
    }
}
