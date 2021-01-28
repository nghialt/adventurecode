package main.kotlin;

fun main() {
    val input = INPUT
//    val input = SAMPLE_INPUT
//    val input = ANOTHER_SAMPLE_INPUT
    println(problem2(input))
}

fun problem1(input: String): Int {
    val bagsMap = parseP1(input)

    val bagsSet = mutableSetOf<String>()
    val candidateList = mutableListOf<String>()
    bagsMap["shiny gold bag"]!!.containableByBags.onEach {
        candidateList.add(it.key)
        bagsSet.add(it.key)
    }

    while (candidateList.isNotEmpty()) {
        val bagName = candidateList.removeAt(0)
        bagsMap[bagName]!!.containableByBags.onEach {
            if (!bagsSet.contains(it.key)) {
                candidateList.add(it.key)
                bagsSet.add(it.key)
            }
        }
    }

    return bagsSet.size
}

fun problem2(input: String): Int {
    var result = 0
    val bagsMap = parseP2(input)
    val candidateList = mutableListOf<Pair<String, Int>>()
    bagsMap["shiny gold bag"]!!.containableBags.onEach {
        candidateList.add(Pair(it.key, it.value))
        result += it.value
    }
    while (candidateList.isNotEmpty()) {
        val bagNamePair = candidateList.removeAt(0)
        bagsMap[bagNamePair.first]!!.containableBags.onEach {
            val addedValue = bagNamePair.second * it.value
            candidateList.add(Pair(it.key, addedValue))
            result += addedValue
        }
    }

    return result
}
