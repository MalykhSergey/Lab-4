package com.example.lab4;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "carsServlet", value = "/cars")
public class CarsServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private CarService carService;

    @Override
    public void init() {
        carService = (CarService) getServletContext().getAttribute("carService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(carService.getCars()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Car car = objectMapper.readValue(request.getInputStream(), Car.class);
        response.getWriter().write(carService.addCar(car).getMessage());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Car> cars = objectMapper.readValue(request.getInputStream(), new TypeReference<List<Car>>() {});
        response.getWriter().write(carService.updateCar(cars.get(0),cars.get(1)).getMessage());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Car car = objectMapper.readValue(request.getInputStream(), Car.class);
        response.getWriter().write(carService.deleteCar(car).getMessage());
    }
}
