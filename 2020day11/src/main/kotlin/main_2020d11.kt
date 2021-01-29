package main.kotlin;

fun main() {
    val input = INPUT
//    val input = SAMPLE_INPUT
    println(problem2(input))
}

fun problem1(input: String): Int {
    val seats = parseSeats(input)

    seats.onEach { println(it) }
    while (true) {
        val cloneSeats = seats.map { it.copyOf() }.toTypedArray()
        for (i in seats.indices) {
            for (j in seats[i].indices) {
                if (cloneSeats[i][j] == '.') {
                    continue
                }

                val occupiedCount = adjacentSeatsOccupiedCount(i, j, cloneSeats)
                if (cloneSeats[i][j] == 'L' && occupiedCount == 0) {
                    seats[i][j] = '#'
                    continue
                }
                if (cloneSeats[i][j] == '#' && occupiedCount >= 4) {
                    seats[i][j] = 'L'
                    continue
                }
            }
        }
        if (cloneSeats
                .asSequence()
                .mapIndexed { index, chars -> chars contentEquals seats[index] }
                .firstOrNull { !it } == null
        ) {
            break
        }
    }
    seats.onEach { println(it) }

    return seats.sumBy { it.count { c -> c == '#' } }
}

val adjacentSeats = listOf(
    Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
    Pair(0, -1), Pair(0, 1),
    Pair(1, -1), Pair(1, 0), Pair(1, 1),
)

fun adjacentSeatsOccupiedCount(i: Int, j: Int, seats: Array<CharArray>): Int {
    var occupiedSeatCount = 0

    for (adjacentSeat in adjacentSeats) {
        val x = i + adjacentSeat.first
        val y = j + adjacentSeat.second
        if (x < 0 || y < 0 || x >= seats.size || y >= seats[x].size) {
            continue
        }
        if (seats[x][y] != '#') {
            continue
        }
        occupiedSeatCount++
    }

    return occupiedSeatCount
}

fun adjacentSeatsOccupiedCountP2(i: Int, j: Int, seats: Array<CharArray>): Int {
    var occupiedSeatCount = 0

    for (adjacentSeat in adjacentSeats) {
        var x = i
        var y = j
        do {
            x += adjacentSeat.first
            y += adjacentSeat.second
            if (x < 0 || y < 0 || x >= seats.size || y >= seats[x].size) {
                break
            }
            if (seats[x][y] == 'L') {
                break
            }
            if (seats[x][y] == '.') {
                continue
            }
            occupiedSeatCount++
            break
        } while (true)
    }

    return occupiedSeatCount
}

fun problem2(input: String): Int {
    val seats = parseSeats(input)

    seats.onEach { println(it) }
    println()
    while (true) {
        val cloneSeats = seats.map { it.copyOf() }.toTypedArray()
        for (i in seats.indices) {
            for (j in seats[i].indices) {
                if (cloneSeats[i][j] == '.') {
                    continue
                }

                val occupiedCount = adjacentSeatsOccupiedCountP2(i, j, cloneSeats)
                if (cloneSeats[i][j] == 'L' && occupiedCount == 0) {
                    seats[i][j] = '#'
                    continue
                }
                if (cloneSeats[i][j] == '#' && occupiedCount >= 5) {
                    seats[i][j] = 'L'
                    continue
                }
            }
        }
        if (cloneSeats
                .asSequence()
                .mapIndexed { index, chars -> chars contentEquals seats[index] }
                .firstOrNull { !it } == null
        ) {
            break
        }
    }
    seats.onEach { println(it) }

    return seats.sumBy { it.count { c -> c == '#' } }
}
