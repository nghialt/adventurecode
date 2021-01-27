package main.kotlin;

fun main() {
    val input = INPUT
    println(problem2(parseRules(input)))
}

fun problem1(rules: List<PasswordRule>): Int {
    var count = 0

    for (rule in rules) {
        var charCount = 0
        for (c in rule.password) {
            if (c == rule.c) {
                ++charCount
            }
        }
        if (rule.range.contains(charCount)) {
            count++
        }
    }

    return count
}

fun problem2(rules: List<PasswordRule>): Int {
    var count = 0

    for (rule in rules) {
        val c = rule.c
        if (rule.password[rule.range.first - 1] == c &&
            rule.password[rule.range.last - 1] != c ||
            rule.password[rule.range.first - 1] != c &&
            rule.password[rule.range.last - 1] == c
        ) {
            count++
        }
    }

    return count
}
