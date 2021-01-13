package main.kotlin

var DEBUG = true

fun log(value: String) {
    if (!DEBUG) {
        return
    }
    println(value)
}
