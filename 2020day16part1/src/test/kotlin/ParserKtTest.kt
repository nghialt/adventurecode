import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.parseNearby
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

    "parse nearby" {
        forAll(
            row(
                """
97,103,89,191,73,79,83,101,151,71,149,53,181,59,61,67,113,109,107,127

nearby tickets:
895,527,676,768,695,821,473
559,796,709,661,116,680,773
               """,
                listOf(895, 527, 676, 768, 695, 821, 473, 559, 796, 709, 661, 116, 680, 773),
            ),
        ) { input, expect ->
            parseNearby(input.split("\n")) shouldBe expect
        }
    }
})