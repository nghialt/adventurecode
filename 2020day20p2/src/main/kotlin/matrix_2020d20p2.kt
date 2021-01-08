package main.kotlin

fun rotateMatrix90(rows: List<String>, clockWise: Boolean): List<String> {
    val matrix = Array(rows.size) { CharArray(rows.size) }

    for (i in rows.indices) {
        for (j in rows.indices) {
            matrix[i][j] = rows[j][i]
        }
    }

    if (clockWise) {
        for (i in rows.indices) {
            for (j in 0 until rows.size / 2) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[i][rows.size - 1 - j]
                matrix[i][rows.size - 1 - j] = temp
            }
        }
    } else {
        for (i in rows.indices) {
            for (j in 0 until rows.size / 2) {
                val temp = matrix[j][i]
                matrix[j][i] = matrix[rows.size - 1 - j][i]
                matrix[rows.size - 1 - j][i] = temp
            }
        }
    }

    return matrix.map { it.joinToString("") }
}

fun flipMatrix(rows: List<String>, horizontal: Boolean): List<String> {
    if (horizontal) {
        return rows.map { it.reversed() }
    }

    val matrix = Array(rows.size) { CharArray(rows.size) }
    for (i in rows.indices) {
        for (j in rows.indices) {
            matrix[i][j] = rows[rows.size - 1 - i][j]
        }
    }

    return matrix.map { it.joinToString("") }
}

fun buildImageMatrix(
    tiles: List<Tile>,
    tilePositionsMap: Map<Int, Pair<Int, Int>>,
    matrixSize: Int,
    leftTopPosition: Pair<Int, Int>,
): Array<Array<Char>> {
    val result = Array(matrixSize) { Array(matrixSize) { Char.MIN_VALUE } }
    tiles.onEach { it.buildContents() }
        .onEach {
            val tilePosition = tilePositionsMap[it.index]!!
            assignMatrixValue(it,
                Pair(
                    tilePosition.first - leftTopPosition.first,
                    tilePosition.second - leftTopPosition.second,
                ),
                result)
        }
    return result
}

fun assignMatrixValue(
    tile: Tile,
    position: Pair<Int, Int>,
    matrix: Array<Array<Char>>,
) {
    val contentSize = tile.contents.size
    val startX = position.first * contentSize
    val startY = position.second * contentSize
    tile.contents
        .onEachIndexed { i, row ->
            row.onEachIndexed { j, c ->
                matrix[startY + i][startX + j] = c
            }
        }
}

fun convertListToArray(rows: List<String>): Array<Array<Char>> {
    val result = Array(rows.size) { Array(rows.first().length) { Char.MIN_VALUE } }
    rows.forEachIndexed { i, row ->
        row.forEachIndexed { j, c ->
            result[i][j] = c
        }
    }
    return result
}

fun countChar(matrix: Array<Array<Char>>, c: Char): Int {
    return matrix.sumBy { row ->
        row.sumBy { if (it == c) 1 else 0 }
    }
}
