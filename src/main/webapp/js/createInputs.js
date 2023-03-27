import {card_list, cars} from "./main.js"
import {createHeaders} from "./createHeaders.js";

function createDeleteButton(car, cardCar) {
    let deleteButton = document.createElement("button")
    deleteButton.type = "submit"
    deleteButton.textContent = "Удалить"
    deleteButton.addEventListener("click", clickEvent => {
        clickEvent.preventDefault()
        if (confirm(`Вы уверены что хотите удалить ${car.brandName} ${car.modelName}?`)) {
            card_list.removeChild(cardCar)
            cars.splice(cars.indexOf(car), 1)
            fetch("/cars", {
                method: "DELETE",
                body: JSON.stringify(car)
            })
        }
    })
    deleteButton.classList.add("btn")
    deleteButton.classList.add("col-3")
    deleteButton.classList.add("btn-danger")
    return deleteButton;
}

function createEditButton(modelNameInput, brandNameInput, yearInput, maxSpeedInput, capacityInput, priceInput, saveButton) {
    let editButton = document.createElement("button")
    editButton.type = "submit"
    editButton.textContent = "Изменить"
    editButton.addEventListener("click", clickEvent => {
        clickEvent.preventDefault()
        modelNameInput.style.display = "block"
        brandNameInput.style.display = "block"
        yearInput.style.display = "block"
        maxSpeedInput.style.display = "block"
        capacityInput.style.display = "block"
        priceInput.style.display = "block"
        saveButton.style.display = "block"
    })
    editButton.classList.add("btn")
    editButton.classList.add("col-3")
    editButton.classList.add("btn-info")
    return editButton;
}

function createSaveButton(cardCar, headers, car, modelNameInput, brandNameInput, yearInput, maxSpeedInput, capacityInput, priceInput, buttonsRow) {
    let saveButton = document.createElement("button")
    saveButton.type = "submit"
    saveButton.style.display = "none"
    saveButton.textContent = "Сохранить"
    let form = cardCar.getElementsByTagName("form");
    form = form[0]
    saveButton.addEventListener("click", clickEvent => {
        clickEvent.preventDefault()
        let newCar = {
            modelName: modelNameInput.value,
            brandName: brandNameInput.value,
            year: yearInput.value,
            maxSpeed: maxSpeedInput.value,
            capacity: capacityInput.value,
            price: priceInput.value
        }
        fetch("/cars", {
            method: "PUT",
            body: JSON.stringify([car, newCar])
        })
        headers = createHeaders(newCar)
        form.replaceChildren(...[headers.modelName, modelNameInput,
            headers.brandName, brandNameInput,
            headers.year, yearInput,
            headers.maxSpeed, maxSpeedInput,
            headers.capacity, capacityInput,
            headers.price, priceInput,
            buttonsRow])
        modelNameInput.style.display = "none"
        brandNameInput.style.display = "none"
        yearInput.style.display = "none"
        maxSpeedInput.style.display = "none"
        capacityInput.style.display = "none"
        priceInput.style.display = "none"
        saveButton.style.display = "none"
        car = newCar
    })
    saveButton.classList.add("btn")
    saveButton.classList.add("col-3")
    saveButton.classList.add("btn-primary")
    return saveButton;
}

export function createInputs(car, cardCar, headers) {
    let modelNameInput = document.createElement("input")
    modelNameInput.type = "text"
    let brandNameInput = document.createElement("input")
    brandNameInput.type = "text"
    let yearInput = document.createElement("input")
    yearInput.type = "number"
    yearInput.min = "1900"
    yearInput.max = "2023"
    let maxSpeedInput = document.createElement("input")
    maxSpeedInput.type = "number"
    maxSpeedInput.min = "1"
    maxSpeedInput.max = "700"
    let capacityInput = document.createElement("input")
    capacityInput.type = "number"
    capacityInput.min = "0.1"
    capacityInput.max = "10"
    capacityInput.step = "0.1"
    let priceInput = document.createElement("input")
    priceInput.type = "number"
    let buttonsRow = document.createElement("div")
    buttonsRow.classList.add("row")
    buttonsRow.classList.add("justify-content-evenly")
    let deleteButton = createDeleteButton(car, cardCar);
    let saveButton = createSaveButton(cardCar, headers, car, modelNameInput, brandNameInput, yearInput, maxSpeedInput, capacityInput, priceInput, buttonsRow);
    let editButton = createEditButton(modelNameInput, brandNameInput, yearInput, maxSpeedInput, capacityInput, priceInput, saveButton);
    buttonsRow.appendChild(deleteButton)
    buttonsRow.appendChild(saveButton)
    buttonsRow.appendChild(editButton)
    let inputs = {
        modelName: modelNameInput,
        brandName: brandNameInput,
        year: yearInput,
        maxSpeed: maxSpeedInput,
        capacity: capacityInput,
        price: priceInput,
        buttonsRow: buttonsRow
    }
    for (let property in car) {
        inputs[property].value = car[property]
        inputs[property].required
        inputs[property].style.display = "none"
        inputs[property].classList.add("form-control")
        inputs[property].classList.add("mb-3")
    }
    return inputs
}