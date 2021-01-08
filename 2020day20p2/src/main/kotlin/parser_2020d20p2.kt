package main.kotlin


fun parseInput(input: String): List<Tile> {
    val tileStrArr = input.trim().split("\n\n")
    return tileStrArr.map { parseTile(it) }
}

fun parseTile(input: String): Tile {
    val lines = input.split("\n").filter { it.isNotEmpty() }
    val indexArr = lines[0].split(" ")
    return Tile(
        indexArr[1].substringBefore(":").toInt(),
        lines.subList(1, lines.size))
}

fun parseMonster(input: String): MonsterPattern {
    return MonsterPattern(
        input.split("\n").filter(String::isNotEmpty),
        input.split("\n").asSequence()
            .filter(String::isNotEmpty)
            .map(String::asIterable)
            .mapIndexed { i, chars ->
                chars
                    .mapIndexed(fun(j, c): Pair<Int, Int>? {
                        if (c == '#') {
                            return Pair(j, i)
                        }
                        return null
                    })
            }.flatten()
            .filterNotNull()
            .toList()
    )
}
