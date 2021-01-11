package main.kotlin

data class Ingredient(val name: String, val allergenCount: MutableMap<String, Int>)

data class Allergen(val name: String, val ingredientCount: MutableMap<String, Int>)

fun cleanAllergen(
    allergen: Allergen,
    ingredientMap: MutableMap<String, Ingredient>,
) {
    val maxIngredientAppearance = allergen.ingredientCount.maxOf { it.value }
    val redundantIngredients = allergen.ingredientCount
        .filterValues { it != maxIngredientAppearance }
        .map { it.key }
    redundantIngredients.forEach {
        allergen.ingredientCount.remove(it)
        ingredientMap[it]!!.allergenCount.remove(allergen.name)
    }
}