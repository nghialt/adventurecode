package main.kotlin

class Tile(
    val index: Int,
    val rows: List<String>,
) {

    override fun toString(): String {
        val rowsStr = rows.joinToString("\n")
        return "$index\n$rowsStr"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Tile) {
            return false
        }

        log("compare tile $index and ${other.index}")
        return index == other.index
    }
}
