package main.kotlin

fun parseSeatsString(input: String): List<String> {
    return input.split("\n")
        .filter(String::isNotEmpty)
}
