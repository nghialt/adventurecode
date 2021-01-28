package main.kotlin;

fun main() {
    val input = INPUT
    println(problem2(input))
}

fun problem1(input: String): Int {
    return parseProblem1(input).sumBy { it.size }
}

fun problem2(input: String): Int {
    return parseProblem2(input).sumBy { it.size }
}
