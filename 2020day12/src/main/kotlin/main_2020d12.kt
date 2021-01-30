package main.kotlin;

fun main() {
    val input = INPUT
//    val input = SAMPLE_INPUT
    println(problem1(input))
}

fun problem1(input: String): Int {
    val instructions = parseInstructions(input)
    instructions.filter { it.action == 'L' || it.action == 'R' }
        .filter { it.value % 90 != 0 }
        .onEach { println(it) }
    var curDirection = 'E'
    var east = 0
    var south = 0
    instructions.onEach { instruction ->
        when (instruction.action) {
            'F' -> {
                when (curDirection) {
                    'E' -> {
                        east += instruction.value
                    }
                    'S' -> {
                        south += instruction.value
                    }
                    'W' -> {
                        east -= instruction.value
                    }
                    'N' -> {
                        south -= instruction.value
                    }
                }
            }
            'E' -> {
                east += instruction.value
            }
            'W' -> {
                east -= instruction.value
            }
            'S' -> {
                south += instruction.value
            }
            'N' -> {
                south -= instruction.value
            }
            'L' -> {
                for (i in 0 until instruction.value / 90)
                    when (curDirection) {
                        'E' -> {
                            curDirection = 'N'
                        }
                        'S' -> {
                            curDirection = 'E'
                        }
                        'W' -> {
                            curDirection = 'S'
                        }
                        'N' -> {
                            curDirection = 'W'
                        }
                    }
            }
            'R' -> {
                for (i in 0 until instruction.value / 90)
                    when (curDirection) {
                        'E' -> {
                            curDirection = 'S'
                        }
                        'S' -> {
                            curDirection = 'W'
                        }
                        'W' -> {
                            curDirection = 'N'
                        }
                        'N' -> {
                            curDirection = 'E'
                        }
                    }
            }
        }
    }

    println("$east $south")
    return east + south
}

fun problem2(input: String): Int {
    return 0
}
