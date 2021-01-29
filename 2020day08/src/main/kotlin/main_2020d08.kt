package main.kotlin;

fun main() {
    val input = INPUT
//    val input = SAMPLE_INPUT
    println(problem2(input))
}

fun problem1(input: String): Int {
    val instructions = parseInstructions(input)
    var currentIndex = 0
    var acc = 0
    val stepsLog = mutableSetOf<Int>()
    while (currentIndex < instructions.size) {
        if (stepsLog.contains(currentIndex)) {
            break
        }
        stepsLog.add(currentIndex)
        val instruction = instructions[currentIndex]
        when (instruction.operation) {
            "acc" -> {
                acc += instruction.value
                currentIndex++
            }
            "jmp" -> currentIndex += instruction.value
            else -> {
                currentIndex++
            }
        }
    }
    return acc
}

fun problem2(input: String): Int {
    val instructions = parseInstructions(input)
    var currentIndex = 0
    var acc = 0
    var stepsLog = mutableSetOf<Int>()
    val changeInsCandidates = mutableListOf<Int>()
    while (currentIndex < instructions.size) {
        if (stepsLog.contains(currentIndex)) {
            break
        }
        stepsLog.add(currentIndex)
        val instruction = instructions[currentIndex]
        when (instruction.operation) {
            "acc" -> {
                acc += instruction.value
                currentIndex++
            }
            "jmp" -> {
                changeInsCandidates.add(currentIndex)
                currentIndex += instruction.value
            }
            else -> {
                changeInsCandidates.add(currentIndex)
                currentIndex++
            }
        }
    }

    while (changeInsCandidates.isNotEmpty()) {
        val changeInsIdx = changeInsCandidates.removeLast()
        val changeIns = instructions[changeInsIdx]
        val cloneInstructions = mutableListOf(*instructions.toTypedArray())
        cloneInstructions[changeInsIdx] = Instruction(
            when (changeIns.operation) {
                "jmp" -> "nop"
                else -> "jmp"
            }, changeIns.value)

        currentIndex = 0
        acc = 0
        stepsLog = mutableSetOf()
        while (currentIndex < cloneInstructions.size) {
            if (stepsLog.contains(currentIndex)) {
                break
            }
            stepsLog.add(currentIndex)
            val instruction = cloneInstructions[currentIndex]
            when (instruction.operation) {
                "acc" -> {
                    acc += instruction.value
                    currentIndex++
                }
                "jmp" -> {
                    currentIndex += instruction.value
                }
                else -> {
                    currentIndex++
                }
            }
        }
        if (currentIndex >= cloneInstructions.size) {
            return acc
        }
    }

    return 0
}

