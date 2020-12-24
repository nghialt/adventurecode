import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.*

class ParserKtTest : StringSpec({

    "parse field string success" {
        forAll(
            row(
                "arrival station: 25-562 or 568-968",
                Pair("arrival station", listOf("25-562", "568-968")),
            ),
            row(
                "arrival platform: 31-672 or 680-969",
                Pair("arrival platform", listOf("31-672", "680-969")),
            ),
            row(
                "arrival track: 43-836 or 852-961",
                Pair("arrival track", listOf("43-836", "852-961")),
            ),
        ) { input, expect ->
            parseFieldString(input) shouldBe expect
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


    "parse field object success" {
        forAll(
            row(
                "arrival station: 25-562 or 568-968",
                Field("arrival station", listOf(Pair(25, 562), Pair(568, 968)))
            ),
            row(
                "arrival platform: 31-672 or 680-969",
                Field("arrival platform", listOf(Pair(31, 672), Pair(680, 969))),
            ),
            row(
                "arrival track: 43-836 or 852-961",
                Field("arrival track", listOf(Pair(43, 836), Pair(852, 961)))
            ),
        ) { input, expect ->
            parseFieldObject(input) shouldBe expect
        }
    }

    "parse nearby" {
        forAll(
            row(
                "case 1",
                """
97,103,89,191,73,79,83,101,151,71,149,53,181,59,61,67,113,109,107,127

nearby tickets:
895,527,676,768,695,821,473
559,796,709,661,116,680,773
               """,
                listOf(Field("arrival station", listOf(Pair(25, 562), Pair(568, 968)))),
                listOf(
                    listOf(895, 527, 676, 768, 695, 821, 473),
                    listOf(559, 796, 709, 661, 116, 680, 773)
                ),
            ),
            row(
                "case 2",
                """
97,103,89,191,73,79,83,101,151,71,149,53,181,59,61,67,113,109,107,127

nearby tickets:
895,527,676,768,695,821,473
559,796,709,661,116,680,773
1,796,709,661,116,680,773
               """,
                listOf(Field("arrival station", listOf(Pair(25, 562), Pair(568, 968)))),
                listOf(
                    listOf(895, 527, 676, 768, 695, 821, 473),
                    listOf(559, 796, 709, 661, 116, 680, 773)
                ),
            ),
        ) { name, input, fields, expect ->
            println(parseAndFilterNearby(input.split("\n"), fields))
            parseAndFilterNearby(input.split("\n"), fields) shouldBe expect
        }
    }
})