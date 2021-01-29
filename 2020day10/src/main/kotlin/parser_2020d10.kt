package main.kotlin

fun parse(input: String): MutableList<Int> {
    return input.split("\n")
        .filter(String::isNotEmpty)
        .map { it.toInt() }
        .toMutableList()
}
