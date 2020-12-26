package main.kotlin

data class AppearanceTable(val rows: List<AppearanceRow>)
data class AppearanceRow(val row: List<AppearanceCell>)
data class AppearanceCell(val row: MutableSet<String>)
data class ColumnStatistic(val fieldMap: Map<String, Int>)
