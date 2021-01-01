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
