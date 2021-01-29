package main.kotlin;

fun main() {
    val input = INPUT
    val preamble = 25
//    val input = SAMPLE_INPUT
//    val preamble = 5
    println(problem2(input, preamble))
}

fun problem1(input: String, preamble: Int): Long {
    val nums = parse(input)
    for (i in preamble until nums.size) {
        val num = nums[i]
        var valid = false
        for (startI in i - preamble until i - 1) {
            for (startJ in startI + 1 until i) {
                if (nums[startI] + nums[startJ] == num) {
                    valid = true
                    break
                }
            }
        }
        if (!valid) {
            return num
        }
    }
    return 0
}

fun problem2(input: String, preamble: Int): Long {
    val invalidNum = problem1(input, preamble)
    val nums = parse(input)
    var startArrIdx = 0
    var endArrIdx = 0
    var sum = nums[0]
    while (sum != invalidNum) {
//        println("sum $sum startArrIdx $startArrIdx endArrIdx $endArrIdx")
        do {
            endArrIdx++
//            println("add endArrIdx $endArrIdx ${nums[endArrIdx]}")
            sum += nums[endArrIdx]
        } while (sum < invalidNum)
//        println("check sum $sum")
        if (sum == invalidNum) {
            break
        }
        do {
            sum -= nums[startArrIdx]
//            println("remove startArrIdx $startArrIdx ${nums[startArrIdx]}")
            startArrIdx++
        } while (sum > invalidNum)
//        println("check sum $sum")
        if (sum == invalidNum) {
            break
        }
    }

    val arr = nums.subList(startArrIdx, endArrIdx + 1)
    println("invalidNum $invalidNum startArrIdx $startArrIdx endArrIdx $endArrIdx")
    println("arr $arr")
    return arr.minOf { it } + arr.maxOf { it }
}

