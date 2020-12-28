import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.*

class ParserKtTest : StringSpec({

    "parseOperationStr" {
        forAll(
            row(
                "case 1",
                "1: \"4\" ",
                Pair("1", EqualOperation("4")),
            ),
            row(
                "case 2",
                "2: 4 2 ",
                Pair("2", AndOperation(NameOperation("4"), NameOperation("2"))),
            ),
            row(
                "case 3",
                "2: 4 2",
                Pair("2", AndOperation(NameOperation("4"), NameOperation("2"))),
            ),
            row(
                "case 4",
                "4: 4 2 | 9 1 ",
                Pair(
                    "4",
                    OrOperation(AndOperation(NameOperation("4"), NameOperation("2")),
                        AndOperation(NameOperation("9"), NameOperation("1"))),
                ),
            ),
            row(
                "case 5",
                "4: 4 2 | 9 1",
                Pair(
                    "4",
                    OrOperation(AndOperation(NameOperation("4"), NameOperation("2")),
                        AndOperation(NameOperation("9"), NameOperation("1"))),
                ),
            ),
            row(
                "case 6",
                "8: 4 | 9",
                Pair(
                    "8",
                    OrOperation(AndOperation(NameOperation("4")),
                        AndOperation(NameOperation("9"))),
                ),
            ),
            row(
                "case 7",
                "8: 4",
                Pair(
                    "8",
                    AndOperation(NameOperation("4")),
                ),
            ),
        ) { _, input, expect ->
            parseOperationStr(input) shouldBe expect
        }
    }
})