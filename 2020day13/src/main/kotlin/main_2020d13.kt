package main.kotlin;

fun main() {
//    val input = INPUT
    val input = SAMPLE_INPUT
    println(problem1(input))
}

fun problem1(input: String): Int {
    val (earliestDeparture, busIDs) = parse(input)
    val chosenBus = busIDs.map { Pair(it, it - earliestDeparture % it) }
        .fold(Pair(0, 0), { acc: Pair<Int, Int>, pair: Pair<Int, Int> ->
            if (acc.first == 0) {
                pair
            } else {
                if (acc.second > pair.second) {
                    pair
                } else {
                    acc
                }
            }

        })
    println("chosenBus $chosenBus")
    return chosenBus.first * chosenBus.second
}

fun problem2(input: String): Int {
    val (_, busIDs) = parse(input)

//    val sameBuses = busIDs.filter {  }
    return 0
}
