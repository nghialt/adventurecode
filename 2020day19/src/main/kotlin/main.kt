package main.kotlin;

fun main() {
    val lines = INPUT.trim().split("\n")
    val optsMap = buildOperations(lines)
    val opt0 = optsMap["0"]!!
    val opt0Size = opt0.size()
    val startIndex = lines.indexOfFirst(String::isEmpty)
    println("$opt0")
    println(lines.subList(startIndex + 1, lines.size).filter { it.length == opt0Size && opt0.evaluate(it) }.size)
}


