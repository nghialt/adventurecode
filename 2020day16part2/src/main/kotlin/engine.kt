package main.kotlin

fun constructAppearanceList(nearbyNums: List<List<Int>>, fields: List<Field>):
        AppearanceTable {
    return AppearanceTable(
        nearbyNums.map { nearby ->
            nearby.map {
                fields.filter { field ->
                    field.ranges.any { range -> it >= range.first && it <= range.second }
                }.map(Field::fieldName)
                    .toSortedSet()
            }.map { AppearanceCell(it) }
                .toList()
        }.map { AppearanceRow(it) }
            .toList()
    )
}

fun statisticFieldsAppearance(input: AppearanceTable, fieldCount: Int): AppearanceStatistic {
    val tmpResult = List(fieldCount) { mutableMapOf<String, Int>() }

    input.rows.forEach { row ->
        row.cells.forEachIndexed { index, cell ->
            cell.fields.forEach { field ->
                val count = tmpResult[index].getOrDefault(field, 0) + 1
                tmpResult[index][field] = count
            }
        }
    }

    return AppearanceStatistic(
        tmpResult
            .map { col -> col.toMap() }
            .map { ColumnStatistic(it) }.toList()
    )
}
