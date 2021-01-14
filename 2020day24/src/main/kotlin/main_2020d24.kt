package main.kotlin;

fun main() {
//    val input = SAMPLE_INPUT
    val input = INPUT
    val patterns = parseInput(input)
    val tilePositionsMap = mutableMapOf<Pair<Double, Double>, Int>()
    tilePositionsMap[Pair(0.0, 0.0)] = 0
    solve(patterns, tilePositionsMap)
    tilePositionsMap.forEach { (t, u) -> println("position $t tile $u") }
    println("part 1 ${countTile(tilePositionsMap, 1)}")
    println()
    val noDay = 100
    for (i in 1..noDay) {
        println("flip day $i")
        fillBorder(tilePositionsMap)
        flipDay(tilePositionsMap)
    }
    val count = countTile(tilePositionsMap, 1)
    println(count)
}

fun fillBorder(tilePositionsMap: MutableMap<Pair<Double, Double>, Int>) {
    val changePositions = mutableListOf<Pair<Double, Double>>()
    tilePositionsMap.forEach { (position, tileColor) ->
        getAdjacentCoordinates(position)
            .filter { tilePositionsMap[it] == null }
            .forEach { changePositions.add(it) }
    }
    changePositions.forEach { tilePositionsMap.putIfAbsent(it, 0) }
}

fun flipDay(tilePositionsMap: MutableMap<Pair<Double, Double>, Int>) {
    val changePositions = mutableMapOf<Pair<Double, Double>, Int>()
    tilePositionsMap.forEach { (position, tileColor) ->
        println("process $position")
        if (tileColor == 0) {
            // white
            val blackTileCount = getAdjacentCoordinates(position)
                .mapNotNull { tilePositionsMap[it] }.filter { it == 1 }.size
            if (blackTileCount == 2) {
                changePositions[position] = 1
            }
        } else {
            // black
            val blackTileCount = getAdjacentCoordinates(position)
                .mapNotNull { tilePositionsMap[it] }.filter { it == 1 }
                .size
            if (blackTileCount == 0 || blackTileCount > 2) {
                println("flip to white blackTileCount $blackTileCount")
                changePositions[position] = 0
            }
        }
    }
    changePositions
        .onEach { println("flip position ${it.key} to ${it.value}") }
        .forEach { (t, u) -> tilePositionsMap[t] = u }
}

fun getAdjacentCoordinates(position: Pair<Double, Double>): List<Pair<Double, Double>> {
    return AdjacentCoordinates.map { Pair(position.first + it.first, position.second + it.second) }
}

fun countTile(tilePositionsMap: Map<Pair<Double, Double>, Int>, tileValue: Int): Int {
    return tilePositionsMap.filter { it.value == tileValue }.count()
}

fun solve(patterns: List<List<Direction>>, tilePositionsMap: MutableMap<Pair<Double, Double>, Int>) {
    patterns.forEach { line ->
//        val position = getFinalPosition(line)
        var position = Pair(0.0, 0.0)
        line.forEach { direction ->
            val move = getCoordinateMovement(direction)
            position = Pair(position.first + move.first, position.second + move.second)
            tilePositionsMap.putIfAbsent(position, 0)
        }
        tilePositionsMap.putIfAbsent(position, 0)
        tilePositionsMap[position] = (tilePositionsMap[position]!! + 1) % 2
    }
}

fun getFinalPosition(line: List<Direction>): Pair<Double, Double> {
    var position = Pair(0.0, 0.0)
    line.forEach { direction ->
        val move = getCoordinateMovement(direction)
        position = Pair(position.first + move.first, position.second + move.second)
        println("direction $direction move $move position $position")
    }
    return position
}
