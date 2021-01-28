package main.kotlin

fun parseP1(input: String): Map<String, BagP1> {
    val bagMap = mutableMapOf<String, BagP1>()
    input.split("\n")
        .filter(String::isNotEmpty)
        .forEach { parseBagP1(it, bagMap) }
    return bagMap
}

fun parseBagP1(input: String, bagsMap: MutableMap<String, BagP1>) {
    val firstSplitArr = input.split(" contain ")
    val bagName = firstSplitArr[0].substring(0, firstSplitArr[0].length - 1)
    if (!bagsMap.containsKey(bagName)) {
        bagsMap[bagName] = BagP1(bagName, mutableMapOf())
    }
    firstSplitArr[1].substring(0, firstSplitArr[1].length - 1)
        .split(", ")
        .mapNotNull { parseContainBag(it) }
        .onEach {
            var containBag = bagsMap[it.first]
            if (containBag == null) {
                containBag = BagP1(it.first, mutableMapOf())
                bagsMap[it.first] = containBag
            }
            containBag.containableByBags[bagName] = it.second
        }
}

fun parseContainBag(input: String): Pair<String, Int>? {
    if (input == "no other bags") {
        return null
    }
    val name = if (input[0] == '1') {
        input.substring(2, input.length)
    } else {
        input.substring(2, input.length - 1)
    }

    return Pair(name, input.substring(0, 1).toInt())
}

fun parseP2(input: String): Map<String, BagP2> {
    val bagMap = mutableMapOf<String, BagP2>()
    input.split("\n")
        .filter(String::isNotEmpty)
        .forEach { parseBagP2(it, bagMap) }
    return bagMap
}

fun parseBagP2(input: String, bagsMap: MutableMap<String, BagP2>) {
    val firstSplitArr = input.split(" contain ")
    val bagName = firstSplitArr[0].substring(0, firstSplitArr[0].length - 1)
    var bag = bagsMap[bagName]
    if (bag == null) {
        bag = BagP2(bagName, mutableMapOf())
        bagsMap[bagName] = bag
    }
    firstSplitArr[1].substring(0, firstSplitArr[1].length - 1)
        .split(", ")
        .mapNotNull { parseContainBag(it) }
        .onEach {
            bag.containableBags[it.first] = it.second
        }
}
