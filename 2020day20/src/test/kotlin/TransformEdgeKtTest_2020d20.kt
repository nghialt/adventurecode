import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.*
import main.kotlin.EdgeDirection.*

class TransformEdgeKtTest_2020d20 : StringSpec({
    "buildOneEdge"{
        forAll(
            row(
                "case 1",
                "1245",
                Tile(1, listOf("1245")),
                BOTTOM,
                listOf(
                    TransformEdge("1245", Tile(1, listOf("1245")),
                        BOTTOM),
                    TransformEdge("5421", Tile(1, listOf("1245")),
                        BOTTOM),
                )
            )
        ) { _, edge, tile, position, expected ->
            buildOneEdge(edge, tile, position) shouldBe expected
        }
    }

    "buildAllEdges"{
        forAll(
            row(
                "case 1",
                Tile(
                    3,
                    listOf(
                        "1234",
                        "5678",
                        "1957",
                        "7567",
                    ),
                ),
                listOf(
                    TransformEdge("1234",
                        Tile(
                            3,
                            listOf(
                                "1234",
                                "5678",
                                "1957",
                                "7567",
                            ),
                        ),
                        TOP),
                    TransformEdge("4321",
                        Tile(
                            3,
                            listOf(
                                "1234",
                                "5678",
                                "1957",
                                "7567",
                            ),
                        ),
                        TOP),
                    TransformEdge("4877",
                        Tile(
                            3,
                            listOf(
                                "1234",
                                "5678",
                                "1957",
                                "7567",
                            ),
                        ),
                        RIGHT),
                    TransformEdge("7784",

                        Tile(
                            3,
                            listOf(
                                "1234",
                                "5678",
                                "1957",
                                "7567",
                            ),
                        ),
                        RIGHT),
                    TransformEdge("7567",
                        Tile(
                            3,
                            listOf(
                                "1234",
                                "5678",
                                "1957",
                                "7567",
                            ),
                        ),
                        BOTTOM),
                    TransformEdge("7657",
                        Tile(
                            3,
                            listOf(
                                "1234",
                                "5678",
                                "1957",
                                "7567",
                            ),
                        ),
                        BOTTOM),
                    TransformEdge("1517",
                        Tile(
                            3,
                            listOf(
                                "1234",
                                "5678",
                                "1957",
                                "7567",
                            ),
                        ),
                        LEFT),
                    TransformEdge("7151",
                        Tile(
                            3,
                            listOf(
                                "1234",
                                "5678",
                                "1957",
                                "7567",
                            ),
                        ),
                        LEFT),
                )
            )
        ) { _, tile, expected ->
            buildAllEdges(tile) shouldBe expected
        }
    }
})
