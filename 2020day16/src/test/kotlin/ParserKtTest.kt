import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.parseRangePair
import main.kotlin.parseRangeRow
import main.kotlin.parseRangeString

class ParserKtTest : StringSpec({

    "parse ranges string success" {
        forAll(
            row(
                "arrival station: 25-562 or 568-968",
                listOf("25-562", "568-968"),
            ),
            row(
                "arrival platform: 31-672 or 680-969",
                listOf("31-672", "680-969"),
            ),
            row(
                "arrival track: 43-836 or 852-961",
                listOf("43-836", "852-961"),
            ),
        ) { input, expect ->
            parseRangeString(input) shouldBe expect
        }
    }

    "parse range string success" {
        forAll(
            row(
                "43-836",
                Pair(43, 836),
            ),
        ) { input, expect ->
            parseRangePair(input) shouldBe expect
        }
    }

    "parse range row success" {
        forAll(
            row(
                "arrival station: 25-562 or 568-968",
                listOf(Pair(25, 562), Pair(568, 968)),
            ),
        ) { input, expect ->
            parseRangeRow(input) shouldBe expect
        }
    }
})