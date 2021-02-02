package main.kotlin;

fun main() {
    val input = INPUT
//    val input = SAMPLE_INPUT
//    val input = SAMPLE_INPUT_1
    println(problem2(input))
}

fun problem1(input: String): Long {
    val instructions = input.parse()
    var mask = ""
    var first1InMask = 0
    val memMap = mutableMapOf<Int, Long>()
    for (instruction in instructions) {
        if (instruction is MaskUpdate) {
            mask = instruction.value
            mask.indexOfFirst { it == '1' }
            continue
        }
        val memUpdate = instruction as MemUpdate
        memMap[memUpdate.position] = memUpdate.value.mask(mask, first1InMask)
    }
    return memMap
        .map { it.value }.sum()
}

fun problem2(input: String): Long {
    val instructions = input.parse()
    var mask = ""
    var first1InMask = 0
    val memMap = mutableMapOf<Long, Long>()
    for (instruction in instructions) {
        if (instruction is MaskUpdate) {
            mask = instruction.value
            mask.indexOfFirst { it == '1' }
            continue
        }
        val memUpdate = instruction as MemUpdate
        val addressMask = memUpdate.position.maskAddress(mask)
        val addresses = addressMask.generateAddressLong()
        addresses.onEach {
            memMap[it] = memUpdate.value
        }
    }
    return memMap
        .map { it.value }.sum()
}
