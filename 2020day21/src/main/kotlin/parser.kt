package main.kotlin


fun parseInputIngredientAppearanceCount(input: String): MutableMap<String, Int> {
    return input.split("\n")
        .map { it.split("(contains")[0] }
        .map { it.split(" ") }
        .flatten()
        .filter(String::isNotEmpty)
        .fold(mutableMapOf(), { map, ingredient ->
            map.putIfAbsent(ingredient, 0)
            map[ingredient] = map[ingredient]!! + 1
            map
        })
}


fun parseInputToMap(input: String): Pair<MutableMap<String, Ingredient>, MutableMap<String, Allergen>> {
    val ingredientMap = mutableMapOf<String, Ingredient>()
    val allergenMap = mutableMapOf<String, Allergen>()

    input.split("\n")
        .filter(String::isNotEmpty)
        .onEach { parseLine(it, ingredientMap, allergenMap) }

    allergenMap.forEach { cleanAllergen(it.value, ingredientMap) }

    return Pair(ingredientMap, allergenMap)
}

fun parseLine(
    line: String,
    ingredientMap: MutableMap<String, Ingredient>,
    allergenMap: MutableMap<String, Allergen>,
) {
    val lineArr = line.split("(contains ")
    val ingredientsStrList = lineArr[0].split(" ").filter(String::isNotEmpty)
    val allergensStrList = lineArr[1].substring(0, lineArr[1].length - 1)
        .trim().split(", ").filter(String::isNotEmpty)
    ingredientsStrList.forEach { ingredientStr ->
        var ingredient = ingredientMap[ingredientStr]
        if (ingredient == null) {
            ingredient = Ingredient(ingredientStr, mutableMapOf())
            ingredientMap[ingredientStr] = ingredient
        }
        allergensStrList.forEach { allergenStr ->
            var allergen = allergenMap[allergenStr]
            if (allergen == null) {
                allergen = Allergen(allergenStr, mutableMapOf())
                allergenMap[allergenStr] = allergen
            }
            allergen.ingredientCount.putIfAbsent(ingredientStr, 0)
            allergen.ingredientCount[ingredientStr] = allergen.ingredientCount[ingredientStr]!! + 1

            ingredient.allergenCount.putIfAbsent(allergenStr, 0)
            ingredient.allergenCount[allergenStr] = ingredient.allergenCount[allergenStr]!! + 1
        }
    }
}
