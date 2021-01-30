package main.kotlin

fun parseInstructions(input: String): List<Instruction> {
    return input.split("\n")
        .filter(String::isNotEmpty)
        .map(::parseInstruction)
}

fun parseInstruction(input: String): Instruction {
    return Instruction(input[0], input.substring(1).toInt())
}
