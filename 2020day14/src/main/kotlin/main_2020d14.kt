package main.kotlin;

fun main() {
    val input = INPUT
//    val input = SAMPLE_INPUT
    println(problem1(input))
}

fun problem1(input: String): Long {
    val instructions = parse(input)
    var mask = ""
    var first1InMask = 0
    val memMap = mutableMapOf<Int, Long>()
    for (instruction in instructions) {
        if (instruction is MaskUpdate) {
            mask = instruction.value
            mask.indexOfFirst { it == '1' }
            print(mask)
            continue
        }
        val memUpdate = instruction as MemUpdate
        memMap[memUpdate.position] = mask(memUpdate.value, mask, first1InMask)
        println("memUpdate $memUpdate ${memMap[memUpdate.position]}")
    }
    return memMap
//        .onEach { println(it) }
        .map { it.value }.sum()
}

fun problem2(input: String): Int {
    return 0
}
