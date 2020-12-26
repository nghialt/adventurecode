package main.kotlin

fun constructAppearanceList(nearbyNums: List<List<Int>>, fields: List<Field>):
        AppearanceTable {
    return AppearanceTable(
        nearbyNums.map { nearby ->
            nearby.map {
                fields.filter { field ->
                    field.ranges.any { range -> it >= range.first && it <= range.second }
                }.map(Field::fieldName)
                    .toMutableSet()
            }.map { AppearanceCell(it) }
                .toList()
        }.map { AppearanceRow(it) }
            .toList()
    )
}

//fun statisticFieldsAppearance(input: List<List<MutableSet<String>>>): List<ColumnStatistic> {
//    return
//}
