import kotlin.random.Random

// Определение классов
data class Wagon(val capacity: Int)
data class Train(val direction: String, val wagons: List<Wagon>)

fun main() {
    val cities = listOf(
        "Бийск", "Барнаул", "Хабаровск", "Сочи", "Красноярск", "Владивосток", "Челябинск",
        "Мурманск", "Казань", "Оренбург", "Томск", "Самара", "Ижевск", "Пермь", "Саратов",
        "Краснодар", "Уфа", "Москва"
    )

    var exitFlag = false
    var currentDirection: String? = null
    var passengersCount = 0
    var train: Train? = null

    // Бесконечный цикл выполнения действий
    while (!exitFlag) {
        println("Выберите действие:")
        println("1. Создать направление")
        println("2. Продать билеты")
        println("3. Сформировать поезд")
        println("4. Отправить поезд")
        println("X. Завершить программу")

        print("Ввод: ")
        val choice = readlnOrNull()

        // Обработка выбора пользователя
        when (choice) {
            "1" -> currentDirection = createDirection(cities)
            "2" -> passengersCount = sellTickets()
            "3" -> train = createTrain(passengersCount)
            "4" -> sendTrain(train, currentDirection)
            "x" -> exitFlag = true
            else -> println("Неправильный выбор. Попробуйте еще раз.\n")
        }
    }
}

// Функция создания направления
fun createDirection(cities: List<String>): String {
    val startCity = cities.random()
    var endCity = cities.random()

    while (endCity == startCity) {
        endCity = cities.random()
    }

    val direction = "$startCity - $endCity"
    println("Направление создано: $direction\n")
    return direction
}

// Функция продажи билетов
fun sellTickets(): Int {
    val passengers = Random.nextInt(5, 202)
    println("Продано билетов: $passengers\n")
    return passengers
}

// Функция создания поезда
fun createTrain(passengersCount: Int): Train {
    val wagons = mutableListOf<Wagon>()
    var remainingPassengers = passengersCount

    // Распределение пассажиров по вагонам
    while (remainingPassengers > 0) {
        // Генерация вместимости вагона
        val wagonCapacity = Random.nextInt(5, 26)
        val passengersInWagon = if (remainingPassengers >= wagonCapacity) wagonCapacity else remainingPassengers
        wagons.add(Wagon(passengersInWagon))
        remainingPassengers -= passengersInWagon
    }

    // Создание поезда
    val train = Train("Направление не указано", wagons)
    println("Поезд сформирован\n")
    return train
}

// Функция отправки поезда
fun sendTrain(train: Train?, direction: String?) {
    if (train != null && direction != null) {
        // Вывод информации о поезде и вагонах
        println("Поезд $direction, состоящий из ${train.wagons.size} вагонов отправлен.")
        for ((index, wagon) in train.wagons.withIndex()) {
            println("Вагон ${index + 1}: Вместимость - ${wagon.capacity}, Пассажиров - ${wagon.capacity}")
        }
        println()

    } else {
        println("Ошибка: Направление или поезд не созданы.\n")
    }
}