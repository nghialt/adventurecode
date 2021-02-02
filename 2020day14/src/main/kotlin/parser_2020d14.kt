package main.kotlin

fun String.parse(): List<Any> {
    return split("\n")
        .filter(String::isNotEmpty)
        .map {
            if (it.startsWith("mask")) {
                it.parseMask()
            } else {
                it.parseMemUpdate()
            }
        }
}

fun String.parseMask(): MaskUpdate {
    val arrSplit = split(" = ")
    return MaskUpdate(arrSplit[1])
}

fun String.parseMemUpdate(): MemUpdate {
    val arrSplit = split(" = ")
    return MemUpdate(arrSplit[0].substring(4, arrSplit[0].length - 1).toInt(),
        arrSplit[1].toLong())
}
