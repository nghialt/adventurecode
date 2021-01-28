import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.BagP1
import main.kotlin.parseBagP1

class Parser_2020d7Test : StringSpec({
    "parseBag"{
        forAll(
            row(
                "case 0",
                "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                mutableMapOf<String, BagP1>(),
                mutableMapOf(
                    Pair("light red bag", BagP1("light red bag", mutableMapOf())),
                    Pair("bright white bag", BagP1("bright white bag",
                        mutableMapOf(
                            Pair("light red bag", 1),
                        ))),
                    Pair("muted yellow bag", BagP1("muted yellow bag", mutableMapOf(
                        Pair("light red bag", 2),
                    ))),
                ),
            ),
            row(
                "case 1",
                "dotted black bags contain no other bags.",
                mutableMapOf(),
                mutableMapOf(
                    Pair("dotted black bag", BagP1("dotted black bag", mutableMapOf())),
                ),
            ),
            row(
                "case 2",
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                mutableMapOf(
                    Pair("faded blue bag", BagP1("faded blue bag", mutableMapOf(
                        Pair("dark green bag", 3),
                    ))),
                ),
                mutableMapOf(
                    Pair("dark olive bag", BagP1("dark olive bag", mutableMapOf())),
                    Pair("faded blue bag", BagP1("faded blue bag", mutableMapOf(
                        Pair("dark olive bag", 3),
                        Pair("dark green bag", 3),
                    ))),
                    Pair("dotted black bag", BagP1("dotted black bag", mutableMapOf(
                        Pair("dark olive bag", 4),
                    ))),
                ),
            ),
        ) { _, input, bagsMap, expectedBagsMap ->
            parseBagP1(input, bagsMap)
            bagsMap shouldBe expectedBagsMap
        }
    }
})
