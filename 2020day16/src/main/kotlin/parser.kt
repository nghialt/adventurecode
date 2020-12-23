package main.kotlin


fun parseRangeString(value: String): List<String> {
    val arrProc1 = value.split(": ")
    return arrProc1[1].split(" or ")
}

fun parseRangePair(value: String): Pair<Int, Int> {
    val arrNum = value.split("-")
    return Pair(arrNum[0].toInt(), arrNum[1].toInt())
}
