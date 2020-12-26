package main.kotlin

data class AppearanceTable(val rows: List<AppearanceRow>)
data class AppearanceRow(val row: List<AppearanceCell>)
data class AppearanceCell(val row: Set<String>)
data class AppearanceStatistic(val colStats: List<ColumnStatistic>)
data class ColumnStatistic(val fieldMap: Map<String, Int>)
