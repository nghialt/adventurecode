package main.kotlin

fun parse(input: String): Pair<Int, List<Int>> {
    val arr = input.split("\n").filter(String::isNotEmpty)
    return Pair(
        arr[0].toInt(),
        arr[1].split(",")
            .filter { it != "x" }
            .map(String::toInt)
    )
}

fun parseP2(input: String): Pair<Int, List<Int>> {
    val arr = input.split("\n").filter(String::isNotEmpty)
    return Pair(
        arr[0].toInt(),
        arr[1].split(",")
            .filter { it != "x" }
            .map(String::toInt)
    )
}
