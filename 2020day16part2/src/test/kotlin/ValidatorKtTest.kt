import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.Field
import main.kotlin.validateNearby

internal class ValidatorKtTest : StringSpec({

    "validate nearby" {
        forAll(
            row(
                "case 1",
                listOf(1, 9, 10),
                listOf(
                    Field("", listOf(Pair(1, 2), Pair(6, 12))),
                ),
                true,
            ),
            row(
                "case 2",
                listOf(1, 9, 10),
                listOf(
                    Field("", listOf(Pair(1, 4), Pair(5, 13))),
                ),
                true,
            ),
            row(
                "case 3",
                listOf(90, 150),
                listOf(
                    Field("", listOf(Pair(2, 4), Pair(5, 13))),
                    Field("", listOf(Pair(59, 99), Pair(150, 200))),
                ),
                true,
            ),
            row(
                "case 4",
                listOf(4, 22),
                listOf(
                    Field("", listOf(Pair(1, 2), Pair(5, 13))),
                    Field("", listOf(Pair(100, 200), Pair(500, 1300))),
                ),
                false,
            ),
            row(
                "case 5",
                listOf(1, 796, 709, 661, 116, 680, 773),
                listOf(Field("", listOf(Pair(25, 562), Pair(568, 968)))),
                false,
            ),
        ) { name, nearbyNums, fields, expect ->
            validateNearby(nearbyNums, fields) shouldBe expect
        }
    }
})