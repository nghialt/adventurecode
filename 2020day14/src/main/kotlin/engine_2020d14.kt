package main.kotlin

import kotlin.math.pow

fun mask(value: Long, mask: String, first1Index: Int): Long {
    var i = 0
    val size = mask.length
    var result = 0L
    var value = value
    while (value != 0L) {
        val curPosition = size - i - 1
        if (mask[curPosition] != 'X') {
            value = value shr 1
            if (mask[curPosition] == '1') {
//                println("i $i add 1")
                result += 2.0.pow(i).toLong()
            }
            i++
            continue
        }
        val curBit = value and 1
        if (curBit == 1L) {
//            println("i $i add 1")
            result += 2.0.pow(i).toLong()
        }
        value = value shr 1
        i++
    }

    while (i < size - first1Index) {
        val curPosition = size - i - 1
        if (mask[curPosition] == '1') {
//            println("i $i add 1")
            result += 2.0.pow(i).toLong()
        }
        i++
    }

    return result
}