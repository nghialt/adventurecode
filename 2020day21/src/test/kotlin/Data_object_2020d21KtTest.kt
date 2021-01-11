import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.Allergen
import main.kotlin.Ingredient
import main.kotlin.cleanAllergen

internal class Data_2020d21KtTest : StringSpec({

    "cleanAllergen"{
        forAll(
            row(
                "case 1",
                Allergen("a", mutableMapOf(
                    Pair("1", 5),
                    Pair("2", 4),
                )),
                mutableMapOf(
                    Pair("1", Ingredient("1", mutableMapOf(
                        Pair("a", 5),
                    ))),
                    Pair("2", Ingredient("2", mutableMapOf(
                        Pair("a", 4),
                    ))),
                ),
                Allergen("a", mutableMapOf(
                    Pair("1", 5),
                )),
                mutableMapOf<String, Ingredient>(
                    Pair("1", Ingredient("1", mutableMapOf(
                        Pair("a", 5),
                    ))),
                    Pair("2", Ingredient("2", mutableMapOf())),
                ),
            ),
        ) { _, allergen, ingredientMap, expectedAllergen, expectedIngredientMap ->
            cleanAllergen(allergen, ingredientMap)
            allergen shouldBe expectedAllergen
            ingredientMap shouldBe expectedIngredientMap
        }
    }
})
