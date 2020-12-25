import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.Field
import main.kotlin.constructAppearanceList

internal class EngineKtTest : StringSpec({
    "constructAppearanceList" {
        forAll(
            row(
                "case 1",
                listOf(
                    listOf(5, 109),
                    listOf(6, 108),
                ),
                listOf(
                    Field("a", listOf(Pair(1, 10), Pair(100, 110))),
                    Field("b", listOf(Pair(8, 20), Pair(108, 120))),
                ),
                arrayOf(
                    mutableSetOf("a"),
                    mutableSetOf("a", "b"),
                ),
            ),
            row(
                "case 2",
                listOf(
                    listOf(9, 111),
                    listOf(2, 20),
                ),
                listOf(
                    Field("a", listOf(Pair(1, 10), Pair(100, 110))),
                    Field("b", listOf(Pair(8, 20), Pair(108, 120))),
                ),
                arrayOf(
                    mutableSetOf("a", "b"),
                    mutableSetOf("b"),
                ),
            )
        ) { _, nearbyNums, fields, expected ->
            constructAppearanceList(nearbyNums, fields) shouldBe expected
        }
    }
})