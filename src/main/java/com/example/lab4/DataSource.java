package com.example.lab4;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private final HikariDataSource hikariDataSource;

    public DataSource() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/lab-4");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("root");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariDataSource = new HikariDataSource(hikariConfig);
        Connection connection = hikariDataSource.getConnection();
        String createTableCarsQuery
                = "CREATE TABLE IF NOT EXISTS cars("
                + "car_pk serial PRIMARY KEY,"
                + "model_name varchar NOT NULL,"
                + "brand_name varchar NOT NULL,"
                + "car_year INTEGER NOT NULL CHECK (car_year BETWEEN 1900 AND 2023),"
                + "max_speed INT NOT NULL CHECK (max_speed BETWEEN 1 AND 700),"
                + "capacity NUMERIC(3,1) NOT NULL CHECK (capacity BETWEEN 0.1 AND 10),"
                + "price INT NOT NULL,"
                + "CONSTRAINT unique_car UNIQUE (model_name, brand_name, car_year, max_speed, capacity, price))";
        connection.createStatement().execute(createTableCarsQuery);
    }

    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    public void close() {
        hikariDataSource.close();
    }
}
