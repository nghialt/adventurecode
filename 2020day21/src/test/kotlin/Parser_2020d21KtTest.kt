import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.kotlin.Allergen
import main.kotlin.Ingredient
import main.kotlin.parseInputIngredientAppearanceCount
import main.kotlin.parseLine

class Parser_2020d21KtTest : StringSpec({
    "parseLine"{
        forAll(
            row(
                "case 1: single allergen",
                "trh fvjkl sbzzf mxmxvkd (contains dairy)",
                mutableMapOf<String, Ingredient>(),
                mutableMapOf<String, Allergen>(),
                mutableMapOf(
                    Pair("trh", Ingredient("trh", mutableMapOf(
                        Pair("dairy", 1),
                    ))),
                    Pair("fvjkl", Ingredient("fvjkl", mutableMapOf(
                        Pair("dairy", 1),
                    ))),
                    Pair("sbzzf", Ingredient("sbzzf", mutableMapOf(
                        Pair("dairy", 1),
                    ))),
                    Pair("mxmxvkd", Ingredient("mxmxvkd", mutableMapOf(
                        Pair("dairy", 1),
                    ))),
                ),
                mutableMapOf(
                    Pair("dairy", Allergen("dairy", mutableMapOf(
                        Pair("trh", 1),
                        Pair("fvjkl", 1),
                        Pair("sbzzf", 1),
                        Pair("mxmxvkd", 1),
                    ))),
                ),
            ),
            row(
                "case 2: multiple allergen",
                "trh fvjkl sbzzf mxmxvkd (contains dairy, fish, soy)",
                mutableMapOf(),
                mutableMapOf(),
                mutableMapOf(
                    Pair("trh", Ingredient("trh", mutableMapOf(
                        Pair("dairy", 1),
                        Pair("fish", 1),
                        Pair("soy", 1),
                    ))),
                    Pair("fvjkl", Ingredient("fvjkl", mutableMapOf(
                        Pair("dairy", 1),
                        Pair("fish", 1),
                        Pair("soy", 1),
                    ))),
                    Pair("sbzzf", Ingredient("sbzzf", mutableMapOf(
                        Pair("dairy", 1),
                        Pair("fish", 1),
                        Pair("soy", 1),
                    ))),
                    Pair("mxmxvkd", Ingredient("mxmxvkd", mutableMapOf(
                        Pair("dairy", 1),
                        Pair("fish", 1),
                        Pair("soy", 1),
                    ))),
                ),
                mutableMapOf(
                    Pair("dairy", Allergen("dairy", mutableMapOf(
                        Pair("trh", 1),
                        Pair("fvjkl", 1),
                        Pair("sbzzf", 1),
                        Pair("mxmxvkd", 1),
                    ))),
                    Pair("soy", Allergen("soy", mutableMapOf(
                        Pair("trh", 1),
                        Pair("fvjkl", 1),
                        Pair("sbzzf", 1),
                        Pair("mxmxvkd", 1),
                    ))),
                    Pair("fish", Allergen("fish", mutableMapOf(
                        Pair("trh", 1),
                        Pair("fvjkl", 1),
                        Pair("sbzzf", 1),
                        Pair("mxmxvkd", 1),
                    ))),
                ),
            ),
            row(
                "case 3: single allergen (has data)",
                "trh fvjkl sbzzf mxmxvkd (contains dairy)",
                mutableMapOf(
                    Pair("trh", Ingredient("trh", mutableMapOf(
                        Pair("dairy", 1),
                    ))),
                ),
                mutableMapOf(
                    Pair("dairy", Allergen("dairy", mutableMapOf(
                        Pair("trh", 1),
                    ))),

                    ),
                mutableMapOf(
                    Pair("trh", Ingredient("trh", mutableMapOf(
                        Pair("dairy", 2),
                    ))),
                    Pair("fvjkl", Ingredient("fvjkl", mutableMapOf(
                        Pair("dairy", 1),
                    ))),
                    Pair("sbzzf", Ingredient("sbzzf", mutableMapOf(
                        Pair("dairy", 1),
                    ))),
                    Pair("mxmxvkd", Ingredient("mxmxvkd", mutableMapOf(
                        Pair("dairy", 1),
                    ))),
                ),
                mutableMapOf(
                    Pair("dairy", Allergen("dairy", mutableMapOf(
                        Pair("trh", 2),
                        Pair("fvjkl", 1),
                        Pair("sbzzf", 1),
                        Pair("mxmxvkd", 1),
                    ))),
                ),
            ),
        ) { _, line, ingredientMap, allergenMap, expectedIngredientMap, expectedAllergenMap ->
            parseLine(line, ingredientMap, allergenMap)
            ingredientMap shouldBe expectedIngredientMap
            allergenMap shouldBe expectedAllergenMap
        }
    }

    "parseInputIngredientAppearanceCount"{
        forAll(
            row(
                "case 1",
                "trh fvjkl sbzzf mxmxvkd (contains dairy)",
                mapOf(
                    Pair("trh", 1),
                    Pair("fvjkl", 1),
                    Pair("sbzzf", 1),
                    Pair("mxmxvkd", 1),
                ),
            ),
            row(
                "case 2",
                """
mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
trh fvjkl sbzzf mxmxvkd (contains dairy)
sqjhc fvjkl (contains soy)
sqjhc mxmxvkd sbzzf (contains fish)
                """.trimIndent(),
                mapOf(
                    Pair("mxmxvkd", 3),
                    Pair("kfcds", 1),
                    Pair("sqjhc", 3),
                    Pair("nhms", 1),
                    Pair("trh", 1),
                    Pair("fvjkl", 2),
                    Pair("sbzzf", 2),
                ),
            ),
        ) { _, input, expected ->
            parseInputIngredientAppearanceCount(input) shouldBe expected
        }
    }
})
