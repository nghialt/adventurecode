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
            row(
                "case 3",
                """
97,103,89,191,73,79,83,101,151,71,149,53,181,59,61,67,113,109,107,127

nearby tickets:
205,558,687,475,90,337,920,665,529,196,456,445,283,55,120,749,60,304,553,389
               """,
                listOf(
                    Field("departure location", listOf(Pair(35, 796), Pair(811, 953))),
                    Field("departure station", listOf(Pair(25, 224), Pair(248, 952))),
                    Field("departure platform", listOf(Pair(47, 867), Pair(885, 959))),
                    Field("departure track", listOf(Pair(44, 121), Pair(127, 949))),
                    Field("departure date", listOf(Pair(49, 154), Pair(180, 960))),
                    Field("departure time", listOf(Pair(35, 532), Pair(546, 971))),
                    Field("arrival location", listOf(Pair(41, 700), Pair(706, 953))),
                    Field("arrival station", listOf(Pair(25, 562), Pair(568, 968))),
                    Field("arrival platform", listOf(Pair(31, 672), Pair(680, 969))),
                    Field("arrival track", listOf(Pair(43, 836), Pair(852, 961))),
                    Field("class", listOf(Pair(38, 291), Pair(304, 968))),
                    Field("duration", listOf(Pair(31, 746), Pair(755, 956))),
                    Field("price", listOf(Pair(46, 711), Pair(719, 971))),
                    Field("route", listOf(Pair(35, 584), Pair(608, 955))),
                    Field("row", listOf(Pair(39, 618), Pair(640, 950))),
                    Field("seat", listOf(Pair(25, 308), Pair(334, 954))),
                    Field("train", listOf(Pair(26, 901), Pair(913, 957))),
                    Field("type", listOf(Pair(33, 130), Pair(142, 965))),
                    Field("wagon", listOf(Pair(34, 395), Pair(405, 962))),
                    Field("zone", listOf(Pair(46, 358), Pair(377, 969))),
                ),
                listOf(
                    listOf(
                        205, 558, 687, 475, 90, 337, 920, 665, 529, 196, 456, 445, 283, 55, 120, 749, 60,
                        304, 553, 389
                    ),
                ),
            ),
        ) { _, input, fields, expect ->
            parseAndFilterNearby(
                input.split(
                    "\n"
                ), fields
            ) shouldBe expect
        }
    }
})