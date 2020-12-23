package main.kotlin;

fun main() {
    val lines: List<String> = INPUT.split("\n")
    val listRanges = constructRanges(lines)
    val nearbyNum = parseNearby(lines)
    val sum = nearbyNum.filter { num -> !isNumberCorrect(num, listRanges) }.sum()
    println(sum)
}

fun helloWorld(): String {
    return "Sub Hello World!"
}

