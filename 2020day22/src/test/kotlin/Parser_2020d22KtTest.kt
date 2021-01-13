import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.Player
import main.kotlin.parsePlayer

class Parser_2020d22KtTest : StringSpec({
    "parsePlayer"{
        forAll(
            row(
                "case 1",
                """
Player 1:
9
2
6
3
10
                """.trimIndent(),
                Player(1, mutableListOf(9, 2, 6, 3, 10)),
            ),
        ) { _, input, expected ->
            parsePlayer(input) shouldBe expected
        }
    }
})
