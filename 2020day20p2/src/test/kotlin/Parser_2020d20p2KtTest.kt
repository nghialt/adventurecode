import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.*

class Parser_2020d20p2KtTest : StringSpec({

    "parseTile"{
        forAll(
            row(
                "case 1",
                """
Tile 2311:
..##.#..#.
##..#.....
#...##..#.
####.#...#
##.##.###.
##...#.###
.#.#.#..##
..#....#..
###...#.#.
..###..###""",
                Tile(2311, listOf(
                    "..##.#..#.",
                    "##..#.....",
                    "#...##..#.",
                    "####.#...#",
                    "##.##.###.",
                    "##...#.###",
                    ".#.#.#..##",
                    "..#....#..",
                    "###...#.#.",
                    "..###..###",
                )),
            ),
        ) { _, input, expected ->
            parseTile(input) shouldBe expected
        }
    }

    "parseInput"{
        forAll(
            row(
                "case 1",
                """
Tile 2311:
..##.#..#.
##..#.....
#...##..#.
####.#...#
##.##.###.
##...#.###
.#.#.#..##
..#....#..
###...#.#.
..###..###

Tile 1951:
#.##...##.
#.####...#
.....#..##
#...######
.##.#....#
.###.#####
###.##.##.
.###....#.
..#.#..#.#
#...##.#..
                """,
                listOf(
                    Tile(2311, listOf(
                        "..##.#..#.",
                        "##..#.....",
                        "#...##..#.",
                        "####.#...#",
                        "##.##.###.",
                        "##...#.###",
                        ".#.#.#..##",
                        "..#....#..",
                        "###...#.#.",
                        "..###..###",
                    )),
                    Tile(1951, listOf(
                        "#.##...##.",
                        "#.####...#",
                        ".....#..##",
                        "#...######",
                        ".##.#....#",
                        ".###.#####",
                        "###.##.##.",
                        ".###....#.",
                        "..#.#..#.#",
                        "#...##.#..",
                    )),
                ),
            ),
        ) { _, input, expected ->
            parseInput(input) shouldBe expected
        }
    }
    "parseMonsterInput"{
        forAll(
            row(
                "case 1",
                """
                     # 
#    ##    ##    ###
 #  #  #  #  #  #   
""",
                MonsterPattern(
                    listOf(
                        "                     # ",
                        "#    ##    ##    ###",
                        " #  #  #  #  #  #   ",
                    ),
                    listOf(
                        Pair(21, 0), Pair(0, 1), Pair(5, 1), Pair(6, 1), Pair(11, 1), Pair(12, 1),
                        Pair(17, 1), Pair(18, 1), Pair(19, 1), Pair(1, 2), Pair(4, 2), Pair(7, 2),
                        Pair(10, 2), Pair(13, 2), Pair(16, 2),
                    )),
            )
        ) { _, input, expected ->
            parseMonster(input) shouldBe expected
        }
    }
})
