import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.EdgeDirection.*
import main.kotlin.Tile
import main.kotlin.TransformEdge
import main.kotlin.buildNextCandidateEdge
import main.kotlin.log

class Engine_2020d20KtTest : StringSpec({

    "buildNextCandidateEdge"{
        forAll(
            row(
                "case 1: T - L",
                false,
                TOP,
                TransformEdge(
                    edge = "147",
                    tile = Tile(
                        index = 1,
                        rows = listOf(
                            "123",
                            "456",
                            "789",
                        ),
                    ),
                    originDirection = LEFT,
                ),
                mapOf<Pair<Int, Int>, Tile>(),
                Pair(0, 0),
                listOf(
                    TransformEdge("369", Tile(0, listOf()), TOP),
                    TransformEdge("987", Tile(0, listOf()), RIGHT),
                    TransformEdge("321", Tile(0, listOf()), LEFT),
                ),
            ),
            row(
                "case 2: T - R",
                false,
                TOP,
                TransformEdge(
                    edge = "369",
                    tile = Tile(
                        index = 1,
                        rows = listOf(
                            "123",
                            "456",
                            "789",
                        ),
                    ),
                    originDirection = RIGHT,
                ),
                mapOf(),
                Pair(0, 0),
                listOf(
                    TransformEdge("147", Tile(0, listOf()), TOP),
                    TransformEdge("789", Tile(0, listOf()), RIGHT),
                    TransformEdge("123", Tile(0, listOf()), LEFT),
                ),
            ),
            row(
                "case 3: T - B",
                false,
                TOP,
                TransformEdge(
                    edge = "789",
                    tile = Tile(
                        index = 1,
                        rows = listOf(
                            "123",
                            "456",
                            "789",
                        ),
                    ),
                    originDirection = BOTTOM,
                ),
                mapOf(),
                Pair(0, 0),
                listOf(
                    TransformEdge("123", Tile(0, listOf()), TOP),
                    TransformEdge("369", Tile(0, listOf()), RIGHT),
                    TransformEdge("147", Tile(0, listOf()), LEFT),
                ),
            ),
            row(
                "case 4: T - T",
                true,
                TOP,
                TransformEdge(
                    edge = "123",
                    tile = Tile(
                        index = 1,
                        rows = listOf(
                            "123",
                            "456",
                            "789",
                        ),
                    ),
                    originDirection = TOP,
                ),
                mapOf(),
                Pair(0, 0),
                listOf(
                    TransformEdge("789", Tile(0, listOf()), TOP),
                    TransformEdge("963", Tile(0, listOf()), RIGHT),
                    TransformEdge("741", Tile(0, listOf()), LEFT),
                ),
            ),
            row(
                "case 5: T - Lr",
                false,
                TOP,
                TransformEdge(
                    edge = "741",
                    tile = Tile(
                        index = 1,
                        rows = listOf(
                            "123",
                            "456",
                            "789",
                        ),
                    ),
                    originDirection = LEFT,
                ),
                mapOf(),
                Pair(0, 0),
                listOf(
                    TransformEdge("963", Tile(0, listOf()), TOP),
                    TransformEdge("321", Tile(0, listOf()), RIGHT),
                    TransformEdge("987", Tile(0, listOf()), LEFT),
                ),
            ),
            row(
                "case 6: T - Rr",
                false,
                TOP,
                TransformEdge(
                    edge = "963",
                    tile = Tile(
                        index = 1,
                        rows = listOf(
                            "123",
                            "456",
                            "789",
                        ),
                    ),
                    originDirection = RIGHT,
                ),
                mapOf(),
                Pair(0, 0),
                listOf(
                    TransformEdge("741", Tile(0, listOf()), TOP),
                    TransformEdge("123", Tile(0, listOf()), RIGHT),
                    TransformEdge("789", Tile(0, listOf()), LEFT),
                ),
            ),
            row(
                "case 7: T - Br",
                false,
                TOP,
                TransformEdge(
                    edge = "987",
                    tile = Tile(
                        index = 1,
                        rows = listOf(
                            "123",
                            "456",
                            "789",
                        ),
                    ),
                    originDirection = BOTTOM,
                ),
                mapOf(),
                Pair(0, 0),
                listOf(
                    TransformEdge("321", Tile(0, listOf()), TOP),
                    TransformEdge("147", Tile(0, listOf()), RIGHT),
                    TransformEdge("369", Tile(0, listOf()), LEFT),
                ),
            ),
            row(
                "case 8: T - Tr",
                false,
                TOP,
                TransformEdge(
                    edge = "321",
                    tile = Tile(
                        index = 1,
                        rows = listOf(
                            "123",
                            "456",
                            "789",
                        ),
                    ),
                    originDirection = TOP,
                ),
                mapOf(),
                Pair(0, 0),
                listOf(
                    TransformEdge("987", Tile(0, listOf()), TOP),
                    TransformEdge("741", Tile(0, listOf()), RIGHT),
                    TransformEdge("963", Tile(0, listOf()), LEFT),
                ),
            ),
            row(
                "case 9: L - R",
                false,
                LEFT,
                TransformEdge(
                    edge = "369",
                    tile = Tile(
                        index = 1,
                        rows = listOf(
                            "123",
                            "456",
                            "789",
                        ),
                    ),
                    originDirection = RIGHT,
                ),
                mapOf(),
                Pair(0, 0),
                listOf(
                    TransformEdge("123", Tile(0, listOf()), TOP),
                    TransformEdge("789", Tile(0, listOf()), BOTTOM),
                    TransformEdge("147", Tile(0, listOf()), LEFT),
                ),
            ),
            row(
                "case 10: L - L",
                false,
                LEFT,
                TransformEdge(
                    edge = "147",
                    tile = Tile(
                        index = 1,
                        rows = listOf(
                            "123",
                            "456",
                            "789",
                        ),
                    ),
                    originDirection = LEFT,
                ),
                mapOf(),
                Pair(0, 0),
                listOf(
                    TransformEdge("321", Tile(0, listOf()), TOP),
                    TransformEdge("987", Tile(0, listOf()), BOTTOM),
                    TransformEdge("369", Tile(0, listOf()), LEFT),
                ),
            ),
            row(
                "case 11: L - T",
                false,
                LEFT,
                TransformEdge(
                    edge = "123",
                    tile = Tile(
                        index = 1,
                        rows = listOf(
                            "123",
                            "456",
                            "789",
                        ),
                    ),
                    originDirection = TOP,
                ),
                mapOf(),
                Pair(0, 0),
                listOf(
                    TransformEdge("741", Tile(0, listOf()), TOP),
                    TransformEdge("963", Tile(0, listOf()), BOTTOM),
                    TransformEdge("789", Tile(0, listOf()), LEFT),
                ),
            ),
            row(
                "case 11: L - B",
                false,
                LEFT,
                TransformEdge(
                    edge = "789",
                    tile = Tile(
                        index = 1,
                        rows = listOf(
                            "123",
                            "456",
                            "789",
                        ),
                    ),
                    originDirection = BOTTOM,
                ),
                mapOf(),
                Pair(0, 0),
                listOf(
                    TransformEdge("147", Tile(0, listOf()), TOP),
                    TransformEdge("369", Tile(0, listOf()), BOTTOM),
                    TransformEdge("123", Tile(0, listOf()), LEFT),
                ),
            ),
        ) { name, enabled, firstEdgeDirection, secondEdge, indicesTileMap, secondTilePosition, expected ->
            val isDebug = false
            if (!isDebug || enabled) {
                log(name)
                val edges = buildNextCandidateEdge(firstEdgeDirection, secondEdge, indicesTileMap, secondTilePosition)
                edges.size shouldBe expected.size
                edges.map { Pair(it.edge, it.originDirection) }
                    .forEachIndexed { i, pair ->
                        pair shouldBe Pair(
                            expected[i].edge,
                            expected[i].originDirection,
                        )
                    }
            }
        }
    }
})

