import {createHeaders} from "./createHeaders.js"
import {createInputs} from "./createInputs.js"
import {card_list} from "./main.js"

export function createCard(car) {
    let cardCar = document.createElement("div")
    cardCar.classList.add("card")
    cardCar.classList.add("mb-3")
    let cardBody = document.createElement("div")
    cardBody.classList.add("card-body")
    cardCar.append(cardBody)
    let form = document.createElement("form")
    cardBody.appendChild(form)
    let headers = createHeaders(car)
    let inputs = createInputs(car, cardCar, headers)
    form.append(
        ...[headers.modelName, inputs.modelName,
            headers.brandName, inputs.brandName,
            headers.year, inputs.year,
            headers.maxSpeed, inputs.maxSpeed,
            headers.capacity, inputs.capacity,
            headers.price, inputs.price, inputs.buttonsRow])
    card_list.insertBefore(cardCar, card_list.firstChild)
}