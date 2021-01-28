import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.Seat
import main.kotlin.constructColumn
import main.kotlin.constructRow
import main.kotlin.constructSeat

class Engine_2020d5Test : StringSpec({
    "constructRow"{
        forAll(
            row(
                "case 0",
                "FBFBBFF",
                44,
            ),
            row(
                "case 1",
                "BFFFBBF",
                70,
            ),
            row(
                "case 2",
                "FFFBBBF",
                14,
            ),
            row(
                "case 3",
                "BBFFBBF",
                102,
            ),
        ) { _, input, expectedRow ->
            constructRow(input) shouldBe expectedRow
        }
    }

    "constructColumn"{
        forAll(
            row(
                "case 0",
                "RRR",
                7,
            ),
            row(
                "case 1",
                "RLL",
                4,
            ),
            row(
                "case 2",
                "RLR",
                5,
            ),
        ) { _, input, expectedRow ->
            constructColumn(input) shouldBe expectedRow
        }
    }

    "constructSeat"{
        forAll(
            row(
                "case 0",
                "BFFFBBFRRR",
                Seat(70, 7, 567),
            ),
            row(
                "case 1",
                "FFFBBBFRRR",
                Seat(14, 7, 119),
            ),
            row(
                "case 2",
                "BBFFBBFRLL",
                Seat(102, 4, 820),
            ),
        ) { _, input, expectedRow ->
            constructSeat(input) shouldBe expectedRow
        }
    }
})
