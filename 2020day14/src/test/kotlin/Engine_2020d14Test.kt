import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.generateAddress
import main.kotlin.generateAddressLong
import main.kotlin.mask
import main.kotlin.maskAddress

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
            value.mask(maskValue, maskValue.indexOfFirst { it == '1' }) shouldBe expected
        }
    }
    "maskAddress"{
        forAll(
            row(
                "case 1",
                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1X1XX0X",
                11,
                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1X1XX1X",
            ),
            row(
                "case 2",
                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                101,
                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            ),
            row(
                "case 3",
                "000000000000000000000000000000X1001X",
                42,
                "000000000000000000000000000000X1101X",
            ),
            row(
                "case 4",
                "00000000000000000000000000000000X0XX",
                26,
                "00000000000000000000000000000001X0XX",
            ),
            row(
                "case 5",
                "10000XX0010100011010011101X1010X1010",
                42272,
                "10000XX0010100011010111101X1011X1010"
            ),
        ) { _, maskValue, value, expected ->
            value.maskAddress(maskValue) shouldBe expected
        }
    }
    "generateAddress"{
        forAll(
            row(
                "case 1",
                "X1101X",
                listOf(
                    listOf(0, 1, 0, 1, 1, 0),
                    listOf(1, 1, 0, 1, 1, 0),
                    listOf(0, 1, 0, 1, 1, 1),
                    listOf(1, 1, 0, 1, 1, 1),
                )
            ),
            row(
                "case 2",
                "X11011",
                listOf(
                    listOf(1, 1, 0, 1, 1, 0),
                    listOf(1, 1, 0, 1, 1, 1),
                )
            ),
            row(
                "case 3",
                "110X11",
                listOf(
                    listOf(1, 1, 0, 0, 1, 1),
                    listOf(1, 1, 1, 0, 1, 1),
                )
            ),
        ) { _, value, expected ->
            value.generateAddress() shouldBe expected
        }
    }
    "generateAddressLong"{
        forAll(
            row(
                "case 1",
                "00X1101X",
                listOf(
                    26L, 27L, 58L, 59L
                )
            ),
            row(
                "case 2",
                "X11011",
                listOf(
                    27L, 59L
                )
            ),
            row(
                "case 3",
                "110X11",
                listOf(
                    51L, 55L
                )
            ),
            row(
                "case 3",
                "110111",
                listOf(
                    55L
                )
            ),
        ) { _, value, expected ->
            value.generateAddressLong() shouldBe expected
        }
    }
})
