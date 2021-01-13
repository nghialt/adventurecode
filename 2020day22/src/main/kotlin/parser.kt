package main.kotlin

fun parsePlayers(input: String): List<Player> {
    return input.split("\n\n").map { it.trim() }.map { parsePlayer(it) }
}

fun parsePlayer(input: String): Player {
    val lines = input.split("\n").map { it.trim() }
    val playerIndex = lines[0].split(" ")[1].replace(":", "").toInt()
    return Player(playerIndex,
        lines.subList(1, lines.size).map { it.toInt() }.toMutableList())
}
