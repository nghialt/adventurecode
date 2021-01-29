package main.kotlin

fun parse(input: String): List<Long> {
    return input.split("\n")
        .filter(String::isNotEmpty)
        .map(String::toLong)
}
