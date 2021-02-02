package main.kotlin;

fun main() {
    val input = SAMPLE_INPUT
//    val input = INPUT

    val partNumber = 1

    MAX = MAX_PART_1
    val cupSize = CUP_SIZE_PART_1
    val time = CUP_MOVE_PART_1

//    MAX = MAX_PART_2
//    val cupSize = CUP_SIZE_PART_2
//    val time = CUP_MOVE_PART_2

    var currItem = input[0]

    val cups = input.toMutableList()
    for (i in 10..cupSize) {
        cups.add(i)
    }
    val cupIndexMap = cups.mapIndexed { i, item -> Pair(item, i) }.toMap().toMutableMap()
    val indexCupMap = cups.mapIndexed { i, item -> Pair(i, item) }.toMap().toMutableMap()
    for (i in 1..time) {
        println("-- move $i --")
        currItem = move(currItem, cups, cupIndexMap)
        log("")
    }
    println("result")
    val cup1Index = cups.indexOf(1)
    println("cup1Index $cup1Index")
    if (partNumber == 1) {
        for (i in 1 until cups.size) {
            val printIndex = (cup1Index + i) % cups.size
            print(cups[printIndex])
        }
    } else {
        val first = (cup1Index + 1) % cups.size
        println("${cups[first]}")
        val second = (cup1Index + 2) % cups.size
        println("${cups[second]}")
    }
}

fun move(currentItem: Int, cups: MutableList<Int>, cupIndexMap: MutableMap<Int, Int>): Int {
    log("cups: $cups")
    log("currItem $currentItem")

    val currIndex = cupIndexMap[currentItem]!!
    val statusArr = Array(cups.size + 1) { false }
    statusArr[currentItem] = true

    val pickupItems = mutableListOf<Int>()
    for (i in 1..3) {
        val item = cups[(currIndex + i) % cups.size]
        pickupItems.add(item)
        statusArr[item] = true
    }
    val updateIndexFrom = cupIndexMap[pickupItems[pickupItems.size - 1]]!! + 1
    log("pick up: $pickupItems")
    val nextIndex = (currIndex + 4) % cups.size
    val nextItem = cups[nextIndex]
    cupIndexMap[nextItem] = (currIndex + 1) % cups.size
    println("nextItem $nextItem ${cupIndexMap[nextItem]}")

    var destItem = nextDestinationItem(currentItem, cups.size)

    while (statusArr[destItem]) {
        destItem = nextDestinationItem(destItem, cups.size)
    }
    log("destination: $destItem")

    cups.removeAll(pickupItems)

    var destIndex = cupIndexMap[destItem]!! - pickupItems.size
    if (destIndex < 0) {
        destIndex = -1
    }
    cups.addAll(destIndex + 1, pickupItems)
    for (i in pickupItems.indices) {
        cupIndexMap[pickupItems[i]] = destIndex + i
    }
    cupIndexMap.forEach { log("$it") }

    return nextItem
}

fun nextDestinationItem(item: Int, size: Int): Int {
    val destItem = (item + size - 1) % size

    if (destItem == 0) {
        return MAX
    }

    return destItem
}