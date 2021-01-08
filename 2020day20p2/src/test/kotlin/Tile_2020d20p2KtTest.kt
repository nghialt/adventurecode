import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.Tile

class Tile_2020d20p2KtTest : StringSpec({

    "Tile.buildContents"{
        forAll(
            row(
                "case 1",
                Tile(
                    0,
                    listOf(
                        "1234",
                        "5678",
                        "9abc",
                        "defg",
                    ),
                ),
                listOf(
                    "67",
                    "ab",
                ),
            ),
        ) { _, tile, expected ->
            tile.buildContents()
            tile.contents shouldBe expected
        }
    }
})
