import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.*
import kotlin.Char.Companion.MIN_VALUE

class Matrix2020d20KtTest : StringSpec({
    "rotateMatrix90"{
        forAll(
            row(
                "90 clockwise",
                listOf(
                    "123",
                    "456",
                    "789",
                ),
                true,
                listOf(
                    "741",
                    "852",
                    "963",
                ),
            ),
            row(
                "90 counter clockwise",
                listOf(
                    "123",
                    "456",
                    "789",
                ),
                false,
                listOf(
                    "369",
                    "258",
                    "147",
                ),
            ),
        ) { _, matrix, clockWise, expected ->
            rotateMatrix90(matrix, clockWise) shouldBe expected
        }
    }
    "flipMatrix"{
        forAll(
            row(
                "horizontal",
                listOf(
                    "123",
                    "456",
                    "789",
                ),
                true,
                listOf(
                    "321",
                    "654",
                    "987",
                ),
            ),
            row(
                "vertical",
                listOf(
                    "123",
                    "456",
                    "789",
                ),
                false,
                listOf(
                    "789",
                    "456",
                    "123",
                ),
            ),
        ) { _, matrix, clockWise, expected ->
            flipMatrix(matrix, clockWise) shouldBe expected
        }
    }

    "assignMatrixValue"{
        forAll(
            row(
                "case 1",
                Tile(
                    1,
                    listOf(
                        "1234",
                        "5678",
                        "9abc",
                        "defg",
                    ),
                ),
                Pair(0, 1),
                Array(4) { Array(4) { MIN_VALUE } },
                arrayOf(
                    charArrayOf(MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE),
                    charArrayOf(MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE),
                    charArrayOf('6', '7', MIN_VALUE, MIN_VALUE),
                    charArrayOf('a', 'b', MIN_VALUE, MIN_VALUE),
                ),
            ),
            row(
                "case 2",
                Tile(
                    1,
                    listOf(
                        "1234",
                        "5678",
                        "9abc",
                        "defg",
                    ),
                ),
                Pair(0, 0),
                Array(4) { Array(4) { MIN_VALUE } },
                arrayOf(
                    charArrayOf('6', '7', MIN_VALUE, MIN_VALUE),
                    charArrayOf('a', 'b', MIN_VALUE, MIN_VALUE),
                    charArrayOf(MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE),
                    charArrayOf(MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE),
                ),
            ),
            row(
                "case 3",
                Tile(
                    1,
                    listOf(
                        "1234",
                        "5678",
                        "9abc",
                        "defg",
                    ),
                ),
                Pair(1, 1),
                Array(4) { Array(4) { MIN_VALUE } },
                arrayOf(
                    charArrayOf(MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE),
                    charArrayOf(MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE),
                    charArrayOf(MIN_VALUE, MIN_VALUE, '6', '7'),
                    charArrayOf(MIN_VALUE, MIN_VALUE, 'a', 'b'),
                ),
            ),
        ) { _, tile, position, matrix, expected ->
            tile.buildContents()
            assignMatrixValue(tile, position, matrix)
            matrix shouldBe expected
        }
    }

    "buildImageMatrix"{
        forAll(
            row(
                "case 1",
                listOf(
                    Tile(1, listOf(
                        "1234",
                        "5678",
                        "9abc",
                        "efgh",
                    )),
                    Tile(9, listOf(
                        "9abc",
                        "1434",
                        "efgh",
                        "5678",
                    )),
                ),
                mapOf(
                    Pair(1, Pair(0, 0)),
                    Pair(9, Pair(1, 1)),
                ),
                4,
                Pair(0, 0),
                arrayOf(
                    charArrayOf('6', '7', MIN_VALUE, MIN_VALUE),
                    charArrayOf('a', 'b', MIN_VALUE, MIN_VALUE),
                    charArrayOf(MIN_VALUE, MIN_VALUE, '4', '3'),
                    charArrayOf(MIN_VALUE, MIN_VALUE, 'f', 'g'),
                ),
            ),
            row(
                "case 2",
                listOf(
                    Tile(1, listOf(
                        "1234",
                        "5678",
                        "9abc",
                        "efgh",
                    )),
                    Tile(9, listOf(
                        "9abc",
                        "1434",
                        "efgh",
                        "5678",
                    )),
                ),
                mapOf(
                    Pair(1, Pair(-1, -1)),
                    Pair(9, Pair(0, 0)),
                ),
                4,
                Pair(-1, -1),
                arrayOf(
                    charArrayOf('6', '7', MIN_VALUE, MIN_VALUE),
                    charArrayOf('a', 'b', MIN_VALUE, MIN_VALUE),
                    charArrayOf(MIN_VALUE, MIN_VALUE, '4', '3'),
                    charArrayOf(MIN_VALUE, MIN_VALUE, 'f', 'g'),
                ),
            ),
            row(
                "case 3",
                listOf(
                    Tile(1, listOf(
                        "1234",
                        "5678",
                        "9abc",
                        "efgh",
                    )),
                    Tile(9, listOf(
                        "9abc",
                        "1434",
                        "efgh",
                        "5678",
                    )),
                ),
                mapOf(
                    Pair(1, Pair(1, 1)),
                    Pair(9, Pair(2, 2)),
                ),
                4,
                Pair(1, 1),
                arrayOf(
                    charArrayOf('6', '7', MIN_VALUE, MIN_VALUE),
                    charArrayOf('a', 'b', MIN_VALUE, MIN_VALUE),
                    charArrayOf(MIN_VALUE, MIN_VALUE, '4', '3'),
                    charArrayOf(MIN_VALUE, MIN_VALUE, 'f', 'g'),
                ),
            ),
        ) { _, tiles, tilePositionsMap, matrixSize, leftTopPosition, expected ->
            buildImageMatrix(tiles, tilePositionsMap, matrixSize, leftTopPosition) shouldBe expected
        }
    }
})