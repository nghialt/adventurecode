package main.kotlin

fun parseNums(input: String): List<Int> {
    return input.trim().split("\n")
        .filter(String::isNotEmpty)
        .map(String::toInt)
}