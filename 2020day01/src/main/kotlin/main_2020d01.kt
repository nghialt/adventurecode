package main.kotlin;

fun main() {
    problem2()
}


fun problem1() {
    val input = INPUT
    val nums = parseNums(input)
    var num0 = 0
    var num1 = 0
    for (i in 0..nums.size - 2) {
        for (j in i + 1 until nums.size) {
            if (nums[i] + nums[j] == 2020) {
                num0 = nums[i]
                num1 = nums[j]
                break
            }
        }
    }

    println("$num0 $num1 ${num0 * num1}")
}

fun problem2() {
    val input = INPUT
    val nums = parseNums(input)
    var num0 = 0
    var num1 = 0
    var num2 = 0
    for (i in 0..nums.size - 3) {
        for (j in i + 1 until nums.size - 1) {
            for (k in j + 1 until nums.size) {
                if (nums[i] + nums[j] + nums[k] == 2020) {
                    num0 = nums[i]
                    num1 = nums[j]
                    num2 = nums[k]
                    break
                }
            }
        }
    }

    println("$num0 $num1 $num2 ${num0 * num1 * num2}")
}
