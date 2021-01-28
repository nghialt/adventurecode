package main.kotlin

fun constructSeat(input: String): Seat {
    val row = constructRow(input.substring(0 until 7))
    val column = constructColumn(input.substring(7 until input.length))
    return Seat(row, column, row * 8 + column)
}

fun constructRow(input: String): Int {
    var lower = 0
    var upper = 127
    return input.fold(0, { _, c ->
        if (c == 'F') {
            upper = lower + (upper - lower) / 2
            lower
        } else {
            lower = (lower + upper + 1) / 2
            upper
        }
    })
}

fun constructColumn(input: String): Int {
    var lower = 0
    var upper = 7
    return input.fold(0, { _, c ->
        if (c == 'L') {
            upper = lower + (upper - lower) / 2
            lower
        } else {
            lower = (lower + upper + 1) / 2
            upper
        }
    })
}
