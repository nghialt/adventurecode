package main.kotlin

data class MonsterPattern(
    val rawRows: List<String>,
    val positions: List<Pair<Int, Int>>,
)

fun hasMonsterPattern(
    monsterPattern: MonsterPattern,
    startY: Int, startX: Int,
    matrix: Array<Array<Char>>,
): Boolean {
    return monsterPattern.positions
        .firstOrNull(fun(it: Pair<Int, Int>): Boolean {
            val result = matrix[startY + it.second][startX + it.first] != '#'
            if (result) {
                println("failed at $it ${startY + it.second} ${startX + it.first}")
            }
            return result
        }) == null
}

fun clearMonsterPattern(
    monsterPattern: MonsterPattern,
    startY: Int, startX: Int,
    matrix: Array<Array<Char>>,
) {
    monsterPattern.positions
        .forEach { matrix[startY + it.second][startX + it.first] = 'O' }
}
