package main.kotlin


fun parseRangeString(value: String): List<String> {
    val arrProc1 = value.split(": ")
    return arrProc1[1].split(" or ")
}

fun parseRangePair(value: String): Pair<Int, Int> {
    val arrNum = value.split("-")
    return Pair(arrNum[0].toInt(), arrNum[1].toInt())
}

fun parseRangeRow(value: String): List<Pair<Int, Int>> {
    return parseRangeString(value).map { rangeStr -> parseRangePair(rangeStr) }
}

fun constructRanges(input: List<String>): List<Pair<Int, Int>> {
    return input.takeWhile { line -> !line.startsWith("your ticket:") }
        .filter { x -> x.isNotEmpty() }
        .map { x -> parseRangeRow(x) }
        .flatten()
}


fun parseNearby(input: List<String>): List<Int> {
    return input.slice(input.indexOf("nearby tickets:") + 1 until input.size)
        .asSequence()
        .filter { line -> line.isNotBlank() }
        .onEach { line -> println("line $line") }
        .map { line -> line.split(",") }
        .flatten()
        .map { numStr -> numStr.toInt() }
        .toList()
}
