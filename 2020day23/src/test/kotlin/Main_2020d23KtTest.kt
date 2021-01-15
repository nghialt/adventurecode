import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.move_

class Main_2020d23KtTest : StringSpec({
    "move"{
        forAll(
            row(
                "case 1",
                3,
                mutableMapOf(
                    Pair(0, 3),
                    Pair(1, 8),
                    Pair(2, 9),
                    Pair(3, 1),
                    Pair(4, 2),
                    Pair(5, 5),
                    Pair(6, 4),
                    Pair(7, 6),
                    Pair(8, 7),
                ),
                mutableMapOf(
                    Pair(3, 0),
                    Pair(8, 1),
                    Pair(9, 2),
                    Pair(1, 3),
                    Pair(2, 4),
                    Pair(5, 5),
                    Pair(4, 6),
                    Pair(6, 7),
                    Pair(7, 8),
                ),
                mutableMapOf(
                    Pair(3, 0),
                    Pair(2, 1),
                    Pair(8, 2),
                    Pair(9, 3),
                    Pair(1, 4),
                    Pair(5, 5),
                    Pair(4, 6),
                    Pair(6, 7),
                    Pair(7, 8),
                ),
                mutableMapOf(
                    Pair(0, 3),
                    Pair(1, 2),
                    Pair(2, 8),
                    Pair(3, 9),
                    Pair(4, 1),
                    Pair(5, 5),
                    Pair(6, 4),
                    Pair(7, 6),
                    Pair(8, 7),
                ),
                2,
            ),
        ) { _, curItem, indexCupsMap, cupIndexMap, expectedIndexCupsMap, expectedCupIndexMap, expectedNextCup ->
            move_(curItem, indexCupsMap, cupIndexMap) shouldBe expectedNextCup
            indexCupsMap shouldBe expectedIndexCupsMap
            cupIndexMap shouldBe expectedCupIndexMap
        }
    }
})
