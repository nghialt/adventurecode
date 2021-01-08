package main.kotlin

class Tile(
    val index: Int,
    val rows: List<String>,
) {
    var contents: List<String> = emptyList()

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

    fun buildContents() {
        contents = rows.subList(1, rows.size - 1).map { it.substring(1, it.length - 1) }
    }
}
