import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.Direction.*
import main.kotlin.parseLine

class Parser_2020d24KtTest : StringSpec({
    "parseLine" {
        forAll(
            row(
                "case 1",
                "eseeswwnwwne",
                listOf(EAST, SOUTH_EAST, EAST, SOUTH_WEST, WEST, NORTH_WEST, WEST, NORTH_EAST),
            ),
        ) { _, input, expected ->
            parseLine(input) shouldBe expected
        }
    }
})
