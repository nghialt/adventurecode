package main.kotlin

fun parseInstructions(input: String): List<Instruction> {
    return input.split("\n")
        .filter(String::isNotEmpty)
        .map {
            val splitArr = it.split(" ")
            Instruction(splitArr[0], splitArr[1].toInt())
        }
}