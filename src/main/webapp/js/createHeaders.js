export function createHeaders(car) {
    let cardModel = document.createElement("h3")
    cardModel.classList.add("card_title")
    cardModel.textContent = car.modelName
    let cardBrand = document.createElement("h3")
    cardBrand.textContent = car.brandName
    let cardYear = document.createElement("h4")
    cardYear.textContent = `Год выпуска: ${car.year}`
    let cardMaxSpeed = document.createElement("h4")
    cardMaxSpeed.textContent = `Максимальная скорость: ${car.maxSpeed}`
    let cardCapacity = document.createElement("h4")
    cardCapacity.textContent = `Объём двигателя: ${car.capacity}`
    let cardPrice = document.createElement("h4")
    cardPrice.textContent = `Цена: ${car.price}`
    cardPrice.classList.add("mb-3")
    return {
        modelName: cardModel,
        brandName:cardBrand,
        year: cardYear,
        maxSpeed: cardMaxSpeed,
        capacity: cardCapacity,
        price: cardPrice
    }
}