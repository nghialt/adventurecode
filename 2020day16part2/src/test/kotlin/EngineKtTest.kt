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
})