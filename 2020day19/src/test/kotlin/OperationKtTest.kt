import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.*

class OperationKtTest : StringSpec({

    "EqualOperation" {
        forAll(
            row(
                "case 1",
                EqualOperation("5"),
                "5", true
            ),
            row(
                "case 2",
                EqualOperation("5"),
                "4", false
            ),
        ) { _, input, eval, expect ->
            input.evaluate(eval) shouldBe expect
            input.size() shouldBe 1
        }
    }

    "AndOperation" {
        forAll(
            row(
                "case 1",
                AndOperation(EqualOperation("5")),
                "5", true, 1
            ),
            row(
                "case 2",
                AndOperation(EqualOperation("5"), EqualOperation("2")),
                "52", true, 2
            ),
            row(
                "case 3",
                AndOperation(EqualOperation("5")),
                "5", true, 1
            ),
            row(
                "false first",
                AndOperation(EqualOperation("5"), EqualOperation("2")),
                "42", false, 2
            ),
            row(
                "false second",
                AndOperation(EqualOperation("5"), EqualOperation("2")),
                "51", false, 2
            ),
        ) { _, input, eval, expectEval, expectSize ->
            input.evaluate(eval) shouldBe expectEval
            input.size() shouldBe expectSize
        }
    }

    "OrOperation" {
        forAll(
            row(
                "case 1",
                OrOperation(
                    AndOperation(EqualOperation("5"), EqualOperation("2")),
                    AndOperation(EqualOperation("3"), EqualOperation("4")),
                ),
                "52", true, 2
            ),
            row(
                "case 2",
                OrOperation(
                    AndOperation(EqualOperation("5"), EqualOperation("2")),
                    AndOperation(EqualOperation("3"), EqualOperation("4")),
                ),
                "34", true, 2
            ),
            row(
                "case 3",
                OrOperation(
                    AndOperation(EqualOperation("5"), EqualOperation("2")),
                    AndOperation(EqualOperation("2"), EqualOperation("4")),
                ),
                "34", false, 2
            ),
            row(
                "case 4",
                OrOperation(
                    AndOperation(EqualOperation("5")),
                    AndOperation(EqualOperation("4")),
                ),
                "5", true, 1
            ),
            row(
                "case 5",
                OrOperation(
                    AndOperation(EqualOperation("5")),
                    AndOperation(EqualOperation("4")),
                ),
                "4", true, 1
            ),
        ) { _, input, eval, expectEval, expectSize ->
            input.evaluate(eval) shouldBe expectEval
            input.size() shouldBe expectSize
        }
    }

    "buildOperations" {
        forAll(
            row(
                "case 1",
                listOf(
                    "0: 1 2",
                    "1: \"a\"",
                    "2: \"b\"",
                ),
                mapOf(
                    Pair(
                        "0",
                        AndOperation(NameOperation("1", EqualOperation("a")),
                            NameOperation("2", EqualOperation("b"))),
                    ),
                    Pair("1", EqualOperation("a")),
                    Pair("2", EqualOperation("b")),
                ),
            ),
            row(
                "case 2",
                listOf(
                    "0: 1 2 | 2 1",
                    "1: 3 4",
                    "2: 4 3",
                    "3: \"a\"",
                    "4: \"b\"",
                ),
                mapOf(
                    Pair(
                        "0",
                        OrOperation(
                            AndOperation(
                                NameOperation(
                                    "1",
                                    AndOperation(NameOperation("3", EqualOperation("a")),
                                        NameOperation("4", EqualOperation("b"))),
                                ),
                                NameOperation(
                                    "2",
                                    AndOperation(NameOperation("4", EqualOperation("b")),
                                        NameOperation("3", EqualOperation("a"))),
                                ),
                            ),
                            AndOperation(
                                NameOperation(
                                    "2",
                                    AndOperation(NameOperation("4", EqualOperation("b")),
                                        NameOperation("3", EqualOperation("a"))),
                                ),
                                NameOperation(
                                    "1",
                                    AndOperation(NameOperation("3", EqualOperation("a")),
                                        NameOperation("4", EqualOperation("b"))),
                                ),
                            ),
                        )
                    ),
                    Pair(
                        "1",
                        AndOperation(NameOperation("3", EqualOperation("a")),
                            NameOperation("4", EqualOperation("b"))),
                    ),
                    Pair(
                        "2",
                        AndOperation(NameOperation("4", EqualOperation("b")),
                            NameOperation("3", EqualOperation("a"))),
                    ),
                    Pair("3", EqualOperation("a")),
                    Pair("4", EqualOperation("b")),
                ),
            ),
        ) { _, lines, expected ->
            buildOperations(lines) shouldBe expected
        }
    }

    "test evaluation" {

        forAll(
            row(
                "case 1",
                buildOperations(listOf(
                    "0: 4 1 5",
                    "1: 2 3 | 3 2",
                    "2: 4 4 | 5 5",
                    "3: 4 5 | 5 4",
                    "4: \"a\"",
                    "5: \"b\"",
                ))["0"]!!,
                listOf("ababbb", "abbbab"),
                true
            ),
            row(
                "case 2",
                buildOperations(listOf(
                    "0: 4 1 5",
                    "1: 2 3 | 3 2",
                    "2: 4 4 | 5 5",
                    "3: 4 5 | 5 4",
                    "4: \"a\"",
                    "5: \"b\"",
                ))["0"]!!,
                listOf("bababa", "aaabbb", "aaaabbb"),
                false,
            )
        ) { _, operation, inputs, expected ->
            for (it in inputs) {
                (operation.evaluate(it) && it.length == operation.size()) shouldBe expected
            }
        }
    }
})