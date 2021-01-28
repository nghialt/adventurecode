package main.kotlin

fun parseProblem1(input: String): List<Set<Char>> {
    return input.split("\n\n")
        .filter { it.isNotEmpty() }
        .map {
            it.split("\n")
                .joinToString("")
                .toCharArray()
                .toSet()
        }
}

fun parseProblem2(input: String): List<Set<Char>> {
    return input.split("\n\n")
        .filter(String::isNotEmpty)
        .map {
            val personAnswers = it.split("\n")
                .filter(String::isNotEmpty)
            personAnswers
                .fold(mutableMapOf(), { acc: MutableMap<Char, Int>, answers: String ->
                    answers.onEach { answer ->
                        acc.putIfAbsent(answer, 0)
                        acc[answer] = acc[answer]!! + 1
                    }
                    acc
                })
                .filterValues { noOfAnswer -> noOfAnswer == personAnswers.size }
                .keys
        }.filter(Set<Char>::isNotEmpty)
}
