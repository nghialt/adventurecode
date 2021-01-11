package main.kotlin;

fun main() {
//    val input = SAMPLE_INPUT
//    val input = INPUT
    val input = INPUT_1
    val ingredientAppearanceMapCount = parseInputIngredientAppearanceCount(input)
    val dataMap = parseInputToMap(input)
    val ingredientCountDec = ingredientAppearanceMapCount
        .filterValues { it == 0 }
        .map { Pair(it.key, it.value) }
        .sortedByDescending { it.second }
    println(ingredientCountDec)
    val ingredientAllergenMap = mutableMapOf<String, String>()
    val allergenIngredientMap = mutableMapOf<String, String>()
    dataMap.second.forEach {
        println("allergen ${it.key} in ${it.value.ingredientCount.keys}")
    }
    println("allergen count ${dataMap.second.size}")
    solve(dataMap, ingredientAllergenMap, allergenIngredientMap)
    ingredientAllergenMap.forEach { (t, u) -> log("ingredient $t allergen $u") }
    ingredientAllergenMap.onEach { ingredientAppearanceMapCount.remove(it.key) }
    log(ingredientAppearanceMapCount.map { it.value }.sum().toString())
    log(allergenIngredientMap.toList().sortedBy { it.first }
        .onEach { println(it) }
        .joinToString(",") { it.second })
}

fun solve(
    dataMap: Pair<MutableMap<String, Ingredient>, MutableMap<String, Allergen>>,
    ingredientAllergenMap: MutableMap<String, String>,
    allergenIngredientMap: MutableMap<String, String>,
) {
    val allergenCount = dataMap.second.size
    while (ingredientAllergenMap.size != allergenCount) {
        val minAllergen = dataMap.second.toList().minByOrNull { it.second.ingredientCount.size }!!.second
        if (minAllergen.ingredientCount.size == 2) {
            println("cannot find a solution")
            return
        }
        val ingredient = minAllergen.ingredientCount.keys.first()
        ingredientAllergenMap[ingredient] = minAllergen.name
        allergenIngredientMap[minAllergen.name] = ingredient
        dataMap.second.remove(minAllergen.name)
        dataMap.second.forEach { it.value.ingredientCount.remove(ingredient) }
    }
}

fun shouldStop(allergenCount: Int, ingredientAllergenMap: MutableMap<String, String>): Boolean {
    if (ingredientAllergenMap.size == 7) {
        return true
    }
    return ingredientAllergenMap.size == allergenCount
}