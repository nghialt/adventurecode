package main.kotlin;

fun main() {
    val input = INPUT

    val map = parseMap(input)
    println(problem1(map, Pair(3, 1)))
    println(problem2(map))
}

fun problem1(map: List<CharArray>, step: Pair<Int, Int>): Int {
    var count = 0

    val colCount = map[0].size
    val bottomLine = map.size - 1
    var i = 0
    var j = 0

    while (i != bottomLine) {
        i += step.second
        j = (j + step.first) % colCount
        if (map[i][j] == '#') {
            count++
        }
    }

    return count
}

fun problem2(map: List<CharArray>): Int {
    val steps = listOf(Pair(1, 1), Pair(3, 1),
        Pair(5, 1), Pair(7, 1), Pair(1, 2))
    var mul = 1

    for (step in steps) {
        mul *= problem1(map, step)
    }

    return mul
}
