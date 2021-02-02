import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.MemUpdate
import main.kotlin.parseMemUpdate

class Parser_2020d14Test : StringSpec({
    "parseMemUpdate"{
        forAll(
            row(
                "case 1",
                "mem[50776] = 68004929",
                MemUpdate(50776, 68004929),
            ),
        ) { _, input, expected ->
            input.parseMemUpdate() shouldBe expected
        }
    }
})
