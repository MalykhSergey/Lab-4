package com.example.lab4;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {
    private final DataSource dataSource;
    private final List<Car> cars;

    public CarService(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        Connection connection = dataSource.getConnection();
        cars = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cars");
            while (resultSet.next()) {
                String modelName = resultSet.getString(2);
                String brandName = resultSet.getString(3);
                int year = resultSet.getInt(4);
                int max_speed = resultSet.getInt(5);
                BigDecimal capacity = resultSet.getBigDecimal(6);
                int price = resultSet.getInt(7);
                cars.add(new Car(modelName, brandName, year, max_speed, capacity, price));
            }
        }
    }
    public List<Car> getCars() {
        return cars;
    }

    synchronized public Result addCar(Car car) {
        if (!cars.contains(car)) {
            cars.add(car);
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cars VALUES (DEFAULT,?,?,?,?,?,?)");
                fillPreparedStatement(preparedStatement, car);
                preparedStatement.execute();

            } catch (SQLException sqlException) {
                return Result.CarIsExists;
            }
            return Result.CarCreated;
        }
        return Result.CarIsExists;
    }

    synchronized public Result deleteCar(Car car) {
        if (cars.remove(car)) {
            try (Connection connection = dataSource.getConnection()) {
                String deleteCarQuery
                        = "DELETE FROM cars WHERE " +
                        "model_name = ? " +
                        "AND brand_name = ?" +
                        "AND car_year = ?" +
                        "AND max_speed = ?" +
                        "AND capacity = ?" +
                        "AND price = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteCarQuery);
                fillPreparedStatement(preparedStatement, car);
                preparedStatement.execute();
            } catch (SQLException sqlException) {
                return Result.CarIsDeletedAlready;
            }
            return Result.CarDeleted;
        } else return Result.CarIsDeletedAlready;
    }

    synchronized public Result updateCar(Car oldCar, Car newCar) {
        int indexOfOldCar = cars.indexOf(oldCar);
        if (indexOfOldCar !=-1) {
            try (Connection connection = dataSource.getConnection()) {
                String deleteCarQuery
                        = "UPDATE cars SET " +
                        "model_name = ?, " +
                        "brand_name = ?, " +
                        "car_year = ?, " +
                        "max_speed = ?, " +
                        "capacity = ?, " +
                        "price = ? " +
                        "WHERE " +
                        "model_name = ? " +
                        "AND brand_name = ?" +
                        "AND car_year = ?" +
                        "AND max_speed = ?" +
                        "AND capacity = ?" +
                        "AND price = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteCarQuery);
                fillPreparedStatement(preparedStatement, newCar);
                preparedStatement.setString(7, oldCar.getModelName());
                preparedStatement.setString(8, oldCar.getBrandName());
                preparedStatement.setInt(9, oldCar.getYear());
                preparedStatement.setInt(10, oldCar.getMaxSpeed());
                preparedStatement.setBigDecimal(11, oldCar.getCapacity());
                preparedStatement.setInt(12, oldCar.getPrice());
                preparedStatement.execute();
            } catch (SQLException sqlException) {
                throw new RuntimeException(sqlException);
            }
            cars.set(indexOfOldCar,newCar);
            return Result.CarUpdated;
        } else return Result.CarIsDeletedAlready;
    }

    private void fillPreparedStatement(PreparedStatement preparedStatement, Car car) throws SQLException {
        preparedStatement.setString(1, car.getModelName());
        preparedStatement.setString(2, car.getBrandName());
        preparedStatement.setInt(3, car.getYear());
        preparedStatement.setInt(4, car.getMaxSpeed());
        preparedStatement.setBigDecimal(5, car.getCapacity());
        preparedStatement.setInt(6, car.getPrice());
    }
}
