package main.kotlin;

fun main() {
    val input = INPUT
//    val input = SAMPLE_INPUT
//    val input = INFINITIVE_LOOP_INPUT
    val players = parsePlayers(input)
    println("Rounds start")
//    val winner = solvePart1(players)
    val winnerIndex = solvePart2(1, players, mutableMapOf())
    val winner =
        if (winnerIndex == players[0].index) {
            players[0]
        } else {
            players[1]
        }
    val cardSize = winner.cards.size
    println()
    println("== Post-game results ==")
    printPlayerCard(players[0])
    printPlayerCard(players[1])
    val result = winner.cards.foldIndexed(0L, fun(i: Int, sum: Long, v: Int): Long {
        return sum + (cardSize - i) * v
    })
    println(result)
}

fun solvePart1(players: List<Player>): Player {
    var round = 0
    var winner: Player? = null
    while (players[0].cards.isNotEmpty() && players[1].cards.isNotEmpty()) {
        round++
        println("round $round")
        val top0 = players[0].getTopCard()
        val top1 = players[1].getTopCard()
        println("$top0 $top1")
        var winCard: Int
        var loseCard: Int
        if (top0 > top1) {
            winner = players[0]
            winCard = top0
            loseCard = top1
        } else {
            winner = players[1]
            winCard = top1
            loseCard = top0
        }
        winner.addCards(winCard, loseCard)
    }

    return winner!!
}

fun solvePart2(gameIndex: Int, players: List<Player>, previousCardsList: MutableMap<String, Boolean>): Int {
    println()
    println("=== Game $gameIndex ===")
    var round = 0
    var winner: Player? = null
    while (players[0].cards.isNotEmpty() && players[1].cards.isNotEmpty()) {
        println()
        round++
        println("-- Round $round (Game $gameIndex) --")
        printPlayerCard(players[0])
        printPlayerCard(players[1])
        val player1CardsStr = players[0].cards.joinToString(", ")
        val player2CardsStr = players[1].cards.joinToString(", ")

        if (previousCardsList[player1CardsStr] == true || previousCardsList[player2CardsStr] == true) {
            println("Prevents infinite games, winner is player 1")
            winner = players[0]
            break
        }
        previousCardsList[player1CardsStr] = true
        previousCardsList[player2CardsStr] = true

        val top0 = players[0].getTopCard()
        val top1 = players[1].getTopCard()
        println("Player 1 plays: $top0")
        println("Player 2 plays: $top1")
        var debugSubgame = false
        val winnerIndex =
            if (shouldStartSubGame(top0, top1, players)) {
                println("Playing a sub-game to determine the winner...")
                debugSubgame = true
                solvePart2(gameIndex + 1,
                    listOf(
                        clonePlayerForSubGame(top0, players[0]),
                        clonePlayerForSubGame(top1, players[1]),
                    ),
                    mutableMapOf())
            } else {
                if (top0 > top1) {
                    players[0].index
                } else {
                    players[1].index
                }
            }
        var winCard: Int
        var loseCard: Int
        if (winnerIndex == players[0].index) {
            winner = players[0]
            winCard = top0
            loseCard = top1
        } else {
            winner = players[1]
            winCard = top1
            loseCard = top0
        }
        println("Player ${winner.index} wins round $round of game $gameIndex!")
        winner.addCards(winCard, loseCard)
        if (debugSubgame) {
            println("back from sub game, after adding cards")
            printPlayerCard(players[0])
            printPlayerCard(players[1])
        }
    }

    println("The winner of game $gameIndex is player ${winner!!.index} !")
    if (gameIndex != 1) {
        println("...anyway, back to game ${gameIndex - 1}.")
    }

    return winner!!.index
}

fun shouldStartSubGame(card0: Int, card1: Int, players: List<Player>): Boolean {
    return card0 <= players[0].cards.size && card1 <= players[1].cards.size
}

fun clonePlayerForSubGame(card: Int, player: Player): Player {
    return Player(player.index, player.cards.subList(0, card).toMutableList())
}

fun printPlayerCard(player: Player) {
    print("Player ${player.index}'s deck: ")
    val player1CardsStr = player.cards.joinToString(", ")
    println(player1CardsStr)
}