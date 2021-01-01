import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.Tile
import main.kotlin.parseInput
import main.kotlin.parseTile

class Parser_2020d20KtTest : StringSpec({

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
})
