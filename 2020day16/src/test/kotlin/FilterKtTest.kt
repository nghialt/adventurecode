import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.isNumberCorrect

internal class FilterKtTest : StringSpec({
    "is number correct" {
        forAll(
            row("correct", 36, listOf(Pair(35, 584), Pair(608, 955), Pair(334, 954)), true),
            row("not correct", 5, listOf(Pair(35, 584), Pair(608, 955), Pair(334, 954)), false),
        ) { name, num, listRange, expected ->
            isNumberCorrect(num, listRange) shouldBe expected
        }
    }
})