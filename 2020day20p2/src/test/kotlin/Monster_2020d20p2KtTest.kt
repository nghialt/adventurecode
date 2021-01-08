import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.MonsterPattern
import main.kotlin.clearMonsterPattern
import main.kotlin.hasMonsterPattern

class Monster_2020d20p2KtTest : StringSpec({

    "hasMonsterPattern"{
        forAll(
            row(
                "case 1",
                MonsterPattern(
                    listOf(" "),
                    listOf(Pair(1, 0), Pair(1, 1), Pair(2, 1)),
                ),
                0, 0,
                arrayOf(
                    arrayOf(' ', '#', ' ', ' '),
                    arrayOf(' ', '#', '#', ' '),
                ),
                true,
            ),
            row(
                "case 2",
                MonsterPattern(
                    listOf(" "),
                    listOf(Pair(1, 0), Pair(1, 1), Pair(3, 1)),
                ),
                0, 0,
                arrayOf(
                    arrayOf(' ', '#', ' ', ' '),
                    arrayOf(' ', '#', '#', ' '),
                ),
                false,
            ),
            row(
                "case 3",
                MonsterPattern(
                    listOf(" "),
                    listOf(Pair(1, 0), Pair(1, 1), Pair(3, 1)),
                ),
                0, 0,
                arrayOf(
                    arrayOf(' ', '#', ' ', ' '),
                    arrayOf(' ', '#', '#', '#'),
                ),
                true,
            ),
        ) { name, monsterPatter, startX, startY, matrix, expected ->
            println(name)
            hasMonsterPattern(monsterPatter, startX, startY, matrix) shouldBe expected
        }
    }
    "clearMonsterPattern"{
        forAll(
            row(
                "case 1",
                MonsterPattern(
                    listOf(" "),
                    listOf(Pair(1, 0), Pair(1, 1), Pair(2, 1)),
                ),
                0, 0,
                arrayOf(
                    arrayOf('#', '#', 'l', 'a'),
                    arrayOf('n', '#', '#', 'n'),
                ),
                arrayOf(
                    arrayOf('#', 'O', 'l', 'a'),
                    arrayOf('n', 'O', 'O', 'n'),
                ),
            ),
            row(
                "case 3",
                MonsterPattern(
                    listOf(" "),
                    listOf(Pair(1, 0), Pair(1, 1), Pair(3, 1)),
                ),
                0, 0,
                arrayOf(
                    arrayOf('#', '#', 'l', 'a'),
                    arrayOf('n', '#', '0', '#'),
                ),
                arrayOf(
                    arrayOf('#', 'O', 'l', 'a'),
                    arrayOf('n', 'O', '0', 'O'),
                ),
            ),
        ) { _, monsterPatter, startX, startY, matrix, expected ->
            clearMonsterPattern(monsterPatter, startX, startY, matrix)
            matrix shouldBe expected
        }
    }
})
