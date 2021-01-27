package main.kotlin

data class PasswordRule(
    val range: IntRange,
    val c: Char,
    val password: String,
)
