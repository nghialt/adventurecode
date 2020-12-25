package main.kotlin

fun constructAppearanceList(nearbyNums: List<List<Int>>, fields: List<Field>):
        List<List<MutableSet<String>>> {
    return nearbyNums.map { nearby ->
        nearby.map {
            fields.filter { field ->
                field.ranges.any { range -> it >= range.first && it <= range.second }
            }.map(Field::fieldName)
                .toMutableSet()
        }
    }
}
