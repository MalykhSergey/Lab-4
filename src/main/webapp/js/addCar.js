import {createCard} from "./createCard.js";
import {brandNameInput, capacityInput, cars, maxSpeedInput, modelNameInput, priceInput, yearInput} from "./main.js";

export function addCar(event) {
    event.preventDefault()
    let car = {
        modelName: modelNameInput.value,
        brandName: brandNameInput.value,
        year: yearInput.value,
        maxSpeed: maxSpeedInput.value,
        capacity: capacityInput.value,
        price: priceInput.value,
    }
    fetch("/cars", {
        method: "POST",
        body: JSON.stringify(car)
    })
    if (!cars.find(stored_car => JSON.stringify(stored_car) === JSON.stringify(car))) {
        cars.push(car)
        createCard(car)
    } else {
        alert("Такая машина уже есть!")
    }
}