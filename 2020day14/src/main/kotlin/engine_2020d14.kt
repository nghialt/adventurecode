package main.kotlin

import kotlin.math.pow

fun Long.mask(mask: String, first1Index: Int): Long {
    var i = 0
    val size = mask.length
    var result = 0L
    var value = this
    while (value != 0L) {
        val curPosition = size - i - 1
        if (mask[curPosition] != 'X') {
            value = value shr 1
            if (mask[curPosition] == '1') {
                result += 2.0.pow(i).toLong()
            }
            i++
            continue
        }
        val curBit = value and 1
        if (curBit == 1L) {
            result += 2.0.pow(i).toLong()
        }
        value = value shr 1
        i++
    }

    while (i < size - first1Index) {
        val curPosition = size - i - 1
        if (mask[curPosition] == '1') {
            result += 2.0.pow(i).toLong()
        }
        i++
    }

    return result
}

fun Int.maskAddress(mask: String): String {
    var i = 0
    val size = mask.length
    var result = StringBuilder(size)
    var value = this
    while (value != 0) {
        val curPosition = size - i - 1
        if (mask[curPosition] == '1' || mask[curPosition] == 'X') {
            result.append(mask[curPosition])
            value = value shr 1
            i++
            continue
        }
        val curBit = value and 1
        result.append((curBit + '0'.toInt()).toChar())
        value = value shr 1
        i++
    }

    val valueSize = result.length
    return mask.substring(0, mask.length - valueSize) + result.toString().reversed()
}

fun String.generateAddress(): List<List<Int>> {
    val result = mutableListOf<MutableList<Int>>()

    for (i in this.indices) {
        val curPosition = this.length - i - 1
        if (this[curPosition] != 'X') {
            if (result.isEmpty()) {
                result.add(mutableListOf(this[curPosition] - '0'))
                continue
            }

            result.onEach { it.add((this[curPosition] - '0')) }
            continue
        }

        if (result.isEmpty()) {
            result.add(mutableListOf(0))
            result.add(mutableListOf(1))
            continue
        }
        result.addAll(result.map(MutableList<Int>::toMutableList))
        val halfSize = result.size / 2
        result.onEachIndexed { index, mutableList ->
            mutableList.add(index / halfSize)
        }
    }

    return result.map { it.toList() }
}

fun String.generateAddressLong(): List<Long> {
    val result = mutableListOf<Long>()

    for (i in this.indices) {
        val curPosition = this.length - i - 1
        if (this[curPosition] != 'X') {
            if (result.isEmpty()) {
                result.add(((this[curPosition] - '0').toLong()))
                continue
            }

            if (this[curPosition] == '0') {
                continue
            }
            result.onEachIndexed { index, _ -> result[index] += 2.0.pow(i).toLong() }
            continue
        }

        if (result.isEmpty()) {
            result.add(0)
            result.add(1)
            continue
        }
        result.addAll(result)
        val halfSize = result.size / 2
        result.subList(halfSize, result.size)
            .onEachIndexed { index, _ -> result[index + halfSize] += 2.0.pow(i).toLong() }
    }

    return result
}
