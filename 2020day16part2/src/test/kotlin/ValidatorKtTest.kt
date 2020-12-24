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
                listOf(1, 9, 10),
                listOf(
                    Field("", listOf(Pair(1, 2))),
                ),
                true,
            ),
            row(
                listOf(1, 9, 10),
                listOf(
                    Field("", listOf(Pair(2, 4), Pair(5, 13))),
                ),
                true,
            ),
            row(
                listOf(90, 100),
                listOf(
                    Field("", listOf(Pair(2, 4), Pair(5, 13))),
                    Field("", listOf(Pair(59, 99), Pair(150, 200))),
                ),
                true,
            ),
            row(
                listOf(4, 22),
                listOf(Field("", listOf(Pair(1, 2), Pair(5, 13)))),
                false,
            ),
        ) { nearbyNums, fields, expect ->
            validateNearby(nearbyNums, fields) shouldBe expect
        }
    }
})