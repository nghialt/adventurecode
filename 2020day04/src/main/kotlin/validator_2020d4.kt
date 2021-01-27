package main.kotlin

val birthYearValidator: (String) -> Boolean = { input ->
    val data = input.toIntOrNull()
    data != null && data >= 1920 && data <= 2002
}

val issueYearValidator = { input: String ->
    val data = input.toIntOrNull()
    data != null && data >= 2010 && data <= 2020
}

fun expirationYearValidator(input: String): Boolean {
    val data = input.toIntOrNull()
    return data != null && data >= 2020 && data <= 2030
}

fun heightValidator(input: String): Boolean {
    if (input.endsWith("cm")) {
        val data = input.substring(0, input.length - 2).toIntOrNull()
        return data != null && data >= 150 && data <= 193
    }
    if (input.endsWith("in")) {
        val data = input.substring(0, input.length - 2).toIntOrNull()
        return data != null && data >= 59 && data <= 76
    }
    return false
}

fun hairColorValidator(input: String): Boolean {
    return input.startsWith("#") &&
            input.substring(1, input.length)
                .firstOrNull {
                    !((it in '0'..'9') || (it in 'a'..'f'))
                } == null
}

val eyeColors = setOf(
    "amb", "blu", "brn", "gry",
    "grn", "hzl", "oth",
)

fun eyeColorValidator(input: String): Boolean {
    return eyeColors.contains(input)
}

val passportRegex = "\\d{9}".toRegex()
fun passportIDValidator(input: String): Boolean {
    return passportRegex.matches(input)
}

fun countryIDValidator(input: String): Boolean {
    return true
}
