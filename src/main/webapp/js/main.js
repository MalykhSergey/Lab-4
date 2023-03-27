import {createCard} from "./createCard.js";
import {addCar} from "./addCar.js";

export let card_list = document.getElementById("cars")
export let cars = []
export let modelNameInput = document.getElementById("modelName")
export let brandNameInput = document.getElementById("brandName")
export let yearInput = document.getElementById("year")
export let maxSpeedInput = document.getElementById("maxSpeed")
export let capacityInput = document.getElementById("capacity")
export let priceInput = document.getElementById("price")
document.getElementsByTagName("form")[0].addEventListener("submit", addCar)

async function cars_upload() {
    let response = await fetch("/cars")
    let uploadedCars = await response.json()
    for (let car of uploadedCars) {
        cars.push(car)
        createCard(car)
    }
}
cars_upload()