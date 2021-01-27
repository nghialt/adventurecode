package main.kotlin;

fun main() {
    val input = INPUT

    val optionalFields = setOf("cid")
    val requiredFields = setOf(
        "byr", "iyr", "eyr", "hgt", "hcl",
        "ecl", "pid",
    )
    println(problem2(parsePassports(input), requiredFields))
}

fun problem1(passports: List<Passport>, requiredFields: Set<String>): Int {
    var count = 0

    for (passport in passports) {
        val passportFields = passport.fields.map { it.first }.toSet()
        var valid = true
        for (field in requiredFields) {
            if (!passportFields.contains(field)) {
                valid = false
                break
            }
        }
        if (valid) {
            ++count
        }
    }


    return count
}

fun problem2(passports: List<Passport>, requiredFields: Set<String>): Int {

    val mapValidator = mapOf(
        Pair("byr", birthYearValidator),
        Pair("iyr", issueYearValidator),
        Pair("eyr", ::expirationYearValidator),
        Pair("hgt", ::heightValidator),
        Pair("hcl", ::hairColorValidator),
        Pair("ecl", ::eyeColorValidator),
        Pair("pid", ::passportIDValidator),
        Pair("cid", ::countryIDValidator),
    )

    var count = 0
    for (passport in passports) {
        val passportFields = passport.fields.toMap()
        var valid = true
        for (field in requiredFields) {
            if (!passportFields.contains(field)) {
                valid = false
                break
            }
            val validator = mapValidator[field] ?: continue
            valid = validator(passportFields[field]!!)
            if (!valid) {
                break
            }
        }
        if (valid) {
            ++count
        }
    }

    return count
}
