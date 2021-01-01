package main.kotlin

enum class EdgeDirection(val value: String, val index: Int) {
    UNKNOWN("unknown", 0),
    TOP("top", 1),
    RIGHT("right", 2),
    BOTTOM("bottom", 3),
    LEFT("left", 4),
}

fun directionFromIndex(index: Int): EdgeDirection {
    return EdgeDirection.values().firstOrNull { it.index == index } ?: EdgeDirection.UNKNOWN
}

data class TransformEdge(
    val edge: String,
    val tile: Tile,
    val originDirection: EdgeDirection,
)

fun buildEdgesMap(tiles: List<Tile>): Map<String, MutableList<TransformEdge>> {
    val edgesCount = mutableMapOf<String, Int>()

    val result = mutableMapOf<String, MutableList<TransformEdge>>()
    tiles.map { buildAllEdges(it) }
        .flatten()
        .map { Pair(it.edge, it) }
        .onEach {
            val edgeCount = edgesCount[it.first]
            if (edgeCount == null) {
                edgesCount[it.first] = 1
            } else {
                edgesCount[it.first] = edgeCount + 1
                if (edgeCount > 2) {
                    throw IllegalStateException("edge ${it.first} is duplicated")
                }
            }
        }
        .onEach {
            val edgeList = result[it.first]
            if (edgeList == null) {
                result[it.first] = mutableListOf(it.second)
            } else {
                edgeList.add(it.second)
            }
        }

    return result
}

fun buildAllEdges(tile: Tile): List<TransformEdge> {
    return listOf(
        buildOneEdge(tile.rows.first(), tile, EdgeDirection.TOP),
        buildOneEdge(tile.rows.map(String::last).joinToString(""), tile, EdgeDirection.RIGHT),
        buildOneEdge(tile.rows.last(), tile, EdgeDirection.BOTTOM),
        buildOneEdge(tile.rows.map(String::first).joinToString(""), tile, EdgeDirection.LEFT),
    ).flatten()
}

fun buildOneEdge(edge: String, tile: Tile, direction: EdgeDirection): List<TransformEdge> {
    return listOf(
        TransformEdge(edge, tile, direction),
        TransformEdge(edge.reversed(), tile, direction),
    )
}
