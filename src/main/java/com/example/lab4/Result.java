package com.example.lab4;

public enum Result {
    CarUpdated("Машина успешно обновлена"),
    CarDeleted("Машина успешно удалена."),
    CarIsDeletedAlready("Машина уже была удалена."),
    CarCreated("Машина успешно добавлена."),
    CarIsExists("Такая машина уже есть!");
    private final String message;

    Result(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
