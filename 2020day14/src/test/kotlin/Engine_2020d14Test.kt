import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.mask

class Engine_2020d14Test : StringSpec({
    "mask"{
        forAll(
            row(
                "case 1",
                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                11L,
                73L,
            ),
            row(
                "case 2",
                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                101L,
                101L,
            ),
        ) { _, maskValue, value, expected ->
            mask(value, maskValue, maskValue.indexOfFirst { it == '1' }) shouldBe expected
        }
    }
})
