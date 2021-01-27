package main.kotlin

fun parseRules(input: String): List<PasswordRule> {
    return input.split("\n").filter(String::isNotEmpty)
        .map { parseRule(it) }
}

fun parseRule(input: String): PasswordRule {
    val arr = input.split(" ")
    val rangeStrArr = arr[0].split("-")
    return PasswordRule(IntRange(rangeStrArr[0].toInt(), rangeStrArr[1].toInt()),
        arr[1][0], arr[2])
}
