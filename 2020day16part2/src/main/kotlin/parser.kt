package main.kotlin


fun parseFieldString(value: String): Pair<String, List<String>> {
    val arrProc1 = value.split(": ")
    return Pair(arrProc1[0], arrProc1[1].split(" or "))
}

fun parseRangePair(value: String): Pair<Int, Int> {
    val arrNum = value.split("-")
    return Pair(arrNum[0].toInt(), arrNum[1].toInt())
}

fun parseFieldObject(line: String): Field {
    val fieldStr = parseFieldString(line)
    return Field(fieldStr.first, fieldStr.second.map { x -> parseRangePair(x) })
}

fun parseNearby(input: List<String>): List<List<Int>> {
    return input.slice(input.indexOf("nearby tickets:") + 1 until input.size)
        .asSequence()
        .filter { line -> line.isNotBlank() }
        .map { line -> line.split(",").map { numStr -> numStr.toInt() } }
        .toList()
}

//fun filterNearby(input: List<String>): List<List<Int>> {
//}
