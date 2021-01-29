package main.kotlin;

fun main() {
    val input = INPUT
//    val input = SAMPLE_INPUT
//    val input = LARGE_SAMPLE_INPUT
    println(problem2(input))
}

fun problem1(input: String): Int {
    val diffMap = mutableMapOf(
        Pair(1, 1),
        Pair(3, 1),
    )
    val adapters = parse(input)
    adapters.sort()
    val diffArr = mutableListOf<Int>()
    for (i in 1 until adapters.size) {
        val diff = adapters[i] - adapters[i - 1]
        diffArr.add(diff)
        if (diffMap.containsKey(diff)) {
            diffMap[diff] = diffMap[diff]!! + 1
        }
    }
    return diffMap[1]!! * diffMap[3]!!
}

fun problem2(input: String): Long {
    val adapters = parse(input)
    adapters.sort()
    adapters.add(0, 0)

    return produceArr(0, adapters, mutableMapOf())
}

fun produceArr(curIndex: Int, arr: List<Int>, numSolutionCountMap: MutableMap<Int, Long>): Long {
    if (curIndex == arr.size - 1) {
        return 1
    }

    var sum = 0L
    for (i in 1..3) {
        val newI = curIndex + i
        if (newI < arr.size) {
            if (arr[newI] - arr[curIndex] < 4) {
                if (numSolutionCountMap.containsKey(arr[newI])) {
                    sum += numSolutionCountMap[arr[newI]]!!
                    continue
                }
                val arrCount = produceArr(newI, arr, numSolutionCountMap)
                numSolutionCountMap[arr[newI]] = arrCount
                sum += arrCount
            }
        }
    }

    return sum
}

