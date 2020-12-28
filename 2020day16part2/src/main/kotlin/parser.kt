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

fun parseFields(lines: List<String>): List<Field> {
    return lines.takeWhile { it.isNotEmpty() }
        .map(::parseFieldObject)
}

fun parseAndFilterNearby(input: List<String>, fields: List<Field>): List<List<Int>> {
    return input.slice(input.indexOf("nearby tickets:") + 1 until input.size)
        .asSequence()
        .filter(String::isNotBlank)
        .map { it.split(",").map(String::toInt) }
        .filter { validateNearby(it, fields) }
        .toList()
}

