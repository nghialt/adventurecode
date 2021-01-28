package main.kotlin;

fun main() {
    val input = INPUT
    println("${problem2(input)}")
}

fun problem1(input: String): Int {
    return parseSeatsString(input)
        .map { constructSeat(it) }
        .maxOf { it.id }
}

fun problem2(input: String): Int {
    val idsSet = parseSeatsString(input)
        .asSequence()
        .map { constructSeat(it) }
        .map {
            when (it.row) {
                0 -> Seat(it.row, it.column, it.id + 1)
                127 -> Seat(it.row, it.column, it.id - 1)
                else -> it
            }
        }
        .map { it.id }
        .toSet()
    for (i in idsSet.minOf { it }..idsSet.size) {
        if (!idsSet.contains(i)) {
            return i
        }
    }
    return 0
}
