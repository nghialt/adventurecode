package main.kotlin

fun constructAppearanceList(nearbyNums: List<List<Int>>, fields: List<Field>): Array<MutableSet<String>> {
    val result = Array(fields.size) { mutableSetOf<String>() }

    nearbyNums.forEach { nearby ->
        nearby.forEachIndexed { index, num ->
            val plusList = fields.filter { field ->
                field.ranges.any { range -> num >= range.first && num <= range.second }
            }
                .map { field -> field.fieldName }
                .toList()
            result[index].addAll(plusList)
        }
    }

    return result
}
