package main.kotlin

fun parsePassports(input: String): List<Passport> {
    return input.split("\n\n")
        .map(::parsePassport)
}

fun parsePassport(input: String): Passport {
    return Passport(
        input.split("\n")
            .filter(String::isNotEmpty)
            .joinToString(" ")
            .split(" ")
            .filter(String::isNotEmpty)
            .map { it.split(":") }
            .map { Pair(it[0], it[1]) }
    )
}
