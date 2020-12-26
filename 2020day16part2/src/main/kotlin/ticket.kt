package main.kotlin

data class ColumnCandidate(
    val fieldNames: List<FieldCandidate>,
    val score: Int // smaller is better}
)

data class FieldCandidate(val fieldName: String, val col: Int)
