package main.kotlin

fun parse(input: String): List<Any> {
    return input.split("\n")
        .filter(String::isNotEmpty)
        .map {
            if (it.startsWith("mask")) {
                parseMask(it)
            } else {
                parseMemUpdate(it)
            }
        }
}

fun parseMask(input: String): MaskUpdate {
    val arrSplit = input.split(" = ")
    return MaskUpdate(arrSplit[1])
}

fun parseMemUpdate(input: String): MemUpdate {
    val arrSplit = input.split(" = ")
    return MemUpdate(arrSplit[0].substring(4, arrSplit[0].length - 1).toInt(),
        arrSplit[1].toLong())
}
