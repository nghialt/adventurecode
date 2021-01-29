package main.kotlin

fun parseSeats(input: String): Array<CharArray> {
    return input.split("\n").filter(String::isNotEmpty)
        .map { it.toCharArray() }.toTypedArray()
}
