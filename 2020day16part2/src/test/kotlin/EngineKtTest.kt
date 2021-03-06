import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.*

internal class EngineKtTest : StringSpec({
    "constructAppearanceList" {
        forAll(
            row(
                "case 1",
                listOf(
                    listOf(3, 9, 18),
                    listOf(15, 1, 5),
                    listOf(5, 14, 9),
                ),

                listOf(
                    Field("class", listOf(Pair(0, 1), Pair(4, 19))),
                    Field("row", listOf(Pair(0, 5), Pair(8, 19))),
                    Field("seat", listOf(Pair(0, 13), Pair(16, 19))),
                ),
                AppearanceTable(
                    listOf(
                        AppearanceRow(
                            listOf(
                                AppearanceCell(setOf("row", "seat")),
                                AppearanceCell(setOf("class", "row", "seat")),
                                AppearanceCell(setOf("class", "row", "seat")),
                            )
                        ),
                        AppearanceRow(
                            listOf(
                                AppearanceCell(setOf("class", "row")),
                                AppearanceCell(setOf("class", "row", "seat")),
                                AppearanceCell(setOf("class", "row", "seat")),
                            )
                        ),
                        AppearanceRow(
                            listOf(
                                AppearanceCell(setOf("class", "row", "seat")),
                                AppearanceCell(setOf("class", "row")),
                                AppearanceCell(setOf("class", "row", "seat")),
                            )
                        ),
                    )
                ),
            )
        ) { _, nearbyNums, fields, expected ->
            constructAppearanceList(nearbyNums, fields) shouldBe expected
        }
    }

    "statisticFieldsAppearance" {
        forAll(
            row(
                "case 1",
                AppearanceTable(
                    listOf(
                        AppearanceRow(
                            listOf(
                                AppearanceCell(setOf("row", "seat")),
                                AppearanceCell(setOf("class", "row", "seat")),
                                AppearanceCell(setOf("class", "row", "seat")),
                            )
                        ),
                        AppearanceRow(
                            listOf(
                                AppearanceCell(setOf("class", "row")),
                                AppearanceCell(setOf("class", "row", "seat")),
                                AppearanceCell(setOf("class", "row", "seat")),
                            )
                        ),
                        AppearanceRow(
                            listOf(
                                AppearanceCell(setOf("class", "row", "seat")),
                                AppearanceCell(setOf("class", "row")),
                                AppearanceCell(setOf("class", "row", "seat")),
                            )
                        ),
                    )
                ),
                3,
                AppearanceStatistic(
                    listOf(
                        ColumnStatistic(
                            mapOf(
                                Pair("row", 3),
                                Pair("seat", 2),
                                Pair("class", 2),
                            )
                        ),
                        ColumnStatistic(
                            mapOf(
                                Pair("row", 3),
                                Pair("seat", 2),
                                Pair("class", 3),
                            )
                        ),
                        ColumnStatistic(
                            mapOf(
                                Pair("row", 3),
                                Pair("seat", 3),
                                Pair("class", 3),
                            )
                        ),
                    )
                ),
            )
        ) { _, appearance, fieldCount, expected ->
            statisticFieldsAppearance(appearance, fieldCount) shouldBe expected
        }
    }

    "reduceFieldsStatistic" {
        forAll(
            row(
                "case 1",
                AppearanceStatistic(
                    listOf(
                        ColumnStatistic(
                            mapOf(
                                Pair("row", 3),
                                Pair("seat", 2),
                                Pair("class", 2),
                            )
                        ),
                        ColumnStatistic(
                            mapOf(
                                Pair("row", 3),
                                Pair("seat", 2),
                                Pair("class", 3),
                            )
                        ),
                        ColumnStatistic(
                            mapOf(
                                Pair("row", 3),
                                Pair("seat", 3),
                                Pair("class", 3),
                            )
                        ),
                    )
                ),
                "row",
                2,
                AppearanceStatistic(
                    listOf(
                        ColumnStatistic(
                            mapOf(
                                Pair("seat", 2),
                                Pair("class", 2),
                            )
                        ),
                        ColumnStatistic(
                            mapOf(
                                Pair("seat", 2),
                                Pair("class", 2),
                            )
                        ),
                        ColumnStatistic(
                            mapOf(
                                Pair("seat", 2),
                                Pair("class", 2),
                            )
                        ),
                    )
                ),
            ),
            row(
                "case 1",
                AppearanceStatistic(
                    listOf(
                        ColumnStatistic(
                            mapOf(
                                Pair("row", 2),
                            )
                        ),
                        ColumnStatistic(
                            mapOf(
                                Pair("row", 2),
                                Pair("seat", 2),
                            )
                        ),
                        ColumnStatistic(
                            mapOf(
                                Pair("seat", 2),
                            )
                        ),
                    )
                ),
                "row",
                1,
                AppearanceStatistic(
                    listOf(
                        ColumnStatistic(
                            mapOf(
                            )
                        ),
                        ColumnStatistic(
                            mapOf(
                                Pair("seat", 1),
                            )
                        ),
                        ColumnStatistic(
                            mapOf(
                                Pair("seat", 1),
                            )
                        ),
                    )
                ),
            ),
        ) { _, appStat, field, fieldCount, expected ->
            reduceFieldsStatistic(appStat, field, fieldCount) shouldBe expected
        }
    }

    "buildColumnCandidates" {
        forAll(
            row(
                "case 1",
                AppearanceStatistic(
                    listOf(
                        ColumnStatistic(
                            mapOf(
                                Pair("row", 3),
                                Pair("seat", 2),
                                Pair("class", 2),
                            )
                        ),
                        ColumnStatistic(
                            mapOf(
                                Pair("row", 3),
                                Pair("seat", 3),
                                Pair("class", 3),
                            )
                        ),
                        ColumnStatistic(
                            mapOf(
                                Pair("row", 3),
                                Pair("seat", 2),
                                Pair("class", 3),
                            )
                        ),
                    )
                ),
                3,
                listOf(
                    ColumnCandidate(
                        listOf(
                            FieldCandidate("row", 0)
                        ), 3
                    ),
                    ColumnCandidate(
                        listOf(
                            FieldCandidate("row", 2),
                            FieldCandidate("class", 2),
                        ), 6
                    ),
                    ColumnCandidate(
                        listOf(
                            FieldCandidate("row", 1),
                            FieldCandidate("seat", 1),
                            FieldCandidate("class", 1),
                        ), 9
                    ),
                ),
            ),
        ) { _, appStat, fieldCount, expected ->
            buildColumnCandidates(appStat, fieldCount) shouldBe expected
        }
    }
})