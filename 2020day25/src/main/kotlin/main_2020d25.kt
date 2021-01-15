package main.kotlin;

fun main() {
//    val cardPublicKey = CARD_SAMPLE_INPUT
//    val doorPublicKey = DOOR_SAMPLE_INPUT
    val cardPublicKey = CARD_INPUT
    val doorPublicKey = DOOR_INPUT
    val subjectNumber = 7
    val divisor = 20201227
    val cardLoopCount = findLoop(subjectNumber, divisor, cardPublicKey)
    println("cardLoopCount $cardLoopCount")
    val doorLoopCount = findLoop(subjectNumber, divisor, doorPublicKey)
    println("doorLoopCount $doorLoopCount")
    val cardEncryptionKey = transform(doorPublicKey, divisor, cardLoopCount)
    println("cardEncryptionKey $cardEncryptionKey")
    val doorEncryptionKey = transform(cardPublicKey, divisor, doorLoopCount)
    println("doorEncryptionKey $doorEncryptionKey")
}

fun findLoop(subjectNumber: Int, divisor: Int, target: Long): Int {
    var loopCount = 0
    var value = 1L

    while (value != target) {
        value = (value * subjectNumber) % divisor
        loopCount++
    }

    return loopCount
}

fun transform(subjectNumber: Long, divisor: Int, loopCount: Int): Long {
    var result = 1L

    for (i in 1..loopCount) {
        result = (result * subjectNumber) % divisor
    }

    return result
}