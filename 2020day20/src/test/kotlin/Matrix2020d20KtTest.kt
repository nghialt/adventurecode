import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.flipMatrix
import main.kotlin.rotateMatrix90

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
})