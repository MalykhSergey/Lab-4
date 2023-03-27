package com.example.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Car implements Serializable {
    private final String modelName;
    private final String brandName;
    private final int year;
    private final int maxSpeed;
    private final BigDecimal capacity;
    private final int price;

    @JsonCreator
    public Car(
            @JsonProperty("modelName") String modelName,
            @JsonProperty("brandName") String brandName,
            @JsonProperty("year") int year,
            @JsonProperty("maxSpeed") int maxSpeed,
            @JsonProperty("capacity") BigDecimal capacity,
            @JsonProperty("price") int price) {
        this.modelName = modelName;
        this.brandName = brandName;
        this.year = year;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
        this.price = price;
    }

    public String getModelName() {
        return modelName;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getYear() {
        return year;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && maxSpeed == car.maxSpeed && price == car.price && modelName.equals(car.modelName) && brandName.equals(car.brandName) && capacity.equals(car.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName, brandName, year, maxSpeed, capacity, price);
    }
}
