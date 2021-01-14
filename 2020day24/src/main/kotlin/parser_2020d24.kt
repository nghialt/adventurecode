package main.kotlin

fun parseInput(input: String): List<List<Direction>> {
    return input.split("\n")
        .map(String::trim)
        .filter(String::isNotEmpty)
        .map(::parseLine)
}

fun parseLine(line: String): List<Direction> {
    val result = mutableListOf<Direction>()
    var newLine = line
    while (newLine.isNotEmpty()) {
        val direction = getDirectionFromAbbr(newLine.substring(0, if (newLine.length > 1) 2 else 1))
        newLine = newLine.substring(direction.abbr.length, newLine.length)
        result.add(direction)
    }

    return result
}