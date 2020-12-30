package main.kotlin

fun parseOperationStr(input: String): Pair<String, Operation> {
    val firstArr = input.split(": ").map(String::trim)
    if (firstArr[1].startsWith("\"")) {
        return Pair(firstArr[0], EqualOperation(firstArr[1].replace("\"", "")))
    }

    val secondArr = firstArr[1].split(" | ")
    val operations = secondArr.map {
        it.split(" ").map(String::trim).map(::NameOperation).toList()
    }
        .map { AndOperation(*it.toTypedArray()) }

    if (secondArr.size == 1) {
        return operations.map { Pair(firstArr[0], it) }.first()
    }


    return Pair(firstArr[0], OrOperation(*operations.toTypedArray()))
}