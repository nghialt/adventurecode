package main.kotlin

fun parseMap(input: String): List<CharArray> {
    return input.split("\n")
        .filter(String::isNotEmpty)
        .map(String::toCharArray)
}
