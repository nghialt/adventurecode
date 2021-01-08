package main.kotlin;

import main.kotlin.EdgeDirection.*
import java.util.*
import kotlin.math.abs

fun main() {
//    val tiles = parseInput(SAMPLE_INPUT)
    val tiles = parseInput(INPUT)
    val edgesMap = buildEdgesMap(tiles)
    val indexTilesMap = mutableMapOf<Pair<Int, Int>, Tile>()
    val tileIndicesMap = mutableMapOf<Int, Pair<Int, Int>>()
    placeImages(tiles, edgesMap, indexTilesMap, tileIndicesMap)
    var position = Pair(0, 0)
    var newPosition = position
    while (indexTilesMap[newPosition] != null) {
        position = newPosition
        newPosition = Pair(newPosition.first - 1, newPosition.second)
    }
    val leftMost = position
    newPosition = leftMost
    while (indexTilesMap[newPosition] != null) {
        position = newPosition
        newPosition = Pair(newPosition.first, newPosition.second - 1)
    }
    val leftTopPosition = position
    println("leftTop $position")
    val leftTopTile = indexTilesMap[position]!!
    newPosition = leftMost
    while (indexTilesMap[newPosition] != null) {
        position = newPosition
        newPosition = Pair(newPosition.first, newPosition.second + 1)
    }
    val leftBottomPosition = position
    println("leftBottom $position")
    val leftBottomTile = indexTilesMap[position]!!

    newPosition = Pair(0, 0)
    while (indexTilesMap[newPosition] != null) {
        position = newPosition
        newPosition = Pair(newPosition.first + 1, newPosition.second)
    }
    val rightMost = position

    newPosition = rightMost
    while (indexTilesMap[newPosition] != null) {
        position = newPosition
        newPosition = Pair(newPosition.first, newPosition.second - 1)
    }
    val rightTopPosition = position
    println("rightTop $position")
    val rightTopTile = indexTilesMap[position]!!
    newPosition = rightMost
    while (indexTilesMap[newPosition] != null) {
        position = newPosition
        newPosition = Pair(newPosition.first, newPosition.second + 1)
    }
    val rightBottomPosition = position
    println("rightBottom $position")
    val rightBottomTile = indexTilesMap[position]!!
    println("leftTopTile ${leftTopTile.index}")
    println("leftBottomTile ${leftBottomTile.index}")
    println("rightTopTile ${rightTopTile.index}")
    println("rightBottomTile ${rightBottomTile.index}")
    println(
        "edges size top ${rightTopPosition.first - leftTopPosition.first} " +
                "right ${rightBottomPosition.second - rightTopPosition.second} " +
                "bottom ${rightBottomPosition.first - leftBottomPosition.first} " +
                "left ${leftBottomPosition.second - leftTopPosition.second}"
    )
    println(leftTopTile.index.toLong() * leftBottomTile.index * rightTopTile.index * rightBottomTile.index)

    val tileSize = tiles.first().rows.size
    val matrixSize = (leftBottomPosition.second - leftTopPosition.second + 1) *
            tiles.first().rows.size
    println("matrixSize $matrixSize")
    var imageMatrix = Array(matrixSize) { Array(matrixSize) { ' ' } }
    for (i in leftTopPosition.second..leftBottomPosition.second) {
        for (j in leftTopPosition.first..rightTopPosition.first) {
            val tile = indexTilesMap[Pair(j, i)]!!
            print("${tile.index} ")
        }
        println()
    }
    val newTiles = indexTilesMap.map { it.value }
    newTiles.forEach {
        val tilePosition = tileIndicesMap[it.index]!!
        val startY = (tilePosition.second - leftTopPosition.second) * tileSize
        val startX = (tilePosition.first - leftTopPosition.first) * tileSize
        for (i in it.rows.indices) {
            for (j in it.rows[i].indices) {
                val y = i + startY
                val x = j + startX
                imageMatrix[y][x] = it.rows[i][j]
            }
        }
    }

    println()
    println("full matrix")
    imageMatrix.forEach { println(it.joinToString("")) }

    val contentTileSize = newTiles.first().rows.size - 2
    val contentMatrixSize = ((rightTopPosition.first - leftTopPosition.first) + 1) * contentTileSize
    var contentMatrix = buildImageMatrix(newTiles, tileIndicesMap, contentMatrixSize, leftTopPosition)
    println()
    println("content matrix original")
    contentMatrix.forEach { println(it.joinToString("")) }
    val monsterPattern = parseMonster(MONSTER_PATTERN)
    println("monster pattern")
    monsterPattern.positions.forEach { print("$it ") }
    contentMatrix = convertListToArray(
        rotateMatrix90(contentMatrix.map { it.joinToString("") }, true)
    )
    contentMatrix = convertListToArray(
        flipMatrix(contentMatrix.map { it.joinToString("") }, true),
    )
//    contentMatrix = convertListToArray(
//        flipMatrix(contentMatrix.map { it.joinToString("") }, false),
//    )
    println()
    println("after transformation")
    contentMatrix
        .forEach { println(it.joinToString("")) }
    val monsterCount = findMonsters(contentMatrix, monsterPattern)
    println()
    contentMatrix.forEach { println(it.joinToString("")) }
    println("monsterCount $monsterCount")
    println("count # ${countChar(contentMatrix, '#')}")
}

fun placeImages(
    tiles: List<Tile>, edgesMap: Map<String, MutableList<TransformEdge>>,
    indexTilesMap: MutableMap<Pair<Int, Int>, Tile>,
    tileIndicesMap: MutableMap<Int, Pair<Int, Int>>,
) {
    val edgesList = LinkedList(buildAllEdges(tiles.first()).filterIndexed { i, _ -> i % 2 == 0 })
    indexTilesMap[Pair(0, 0)] = tiles.first()
    tileIndicesMap[tiles.first().index] = Pair(0, 0)
    log("tile ${tiles.first().index} at ${Pair(0, 0)}")
    while (!edgesList.isEmpty()) {
        val firstEdge = edgesList.removeFirst()
        val firstTile = firstEdge.tile
        val position = tileIndicesMap[firstTile.index]!!
        val edgesRef = edgesMap[firstEdge.edge]
        if (edgesRef!!.size < 2) {
            continue
        }
        log("=== process tile ${firstTile.index} ${firstEdge.edge}")
        val secondEdge = edgesRef.first { it.tile.index != firstEdge.tile.index }
        if (tileIndicesMap[secondEdge.tile.index] != null) {
            continue
        }
        val newPosition = when (firstEdge.originDirection) {
            TOP -> {
                Pair(position.first, position.second - 1)
            }
            LEFT -> {
                Pair(position.first - 1, position.second)
            }
            RIGHT -> {
                Pair(position.first + 1, position.second)
            }
            BOTTOM -> {
                Pair(position.first, position.second + 1)
            }
            else -> TODO()
        }
        log("tile ${secondEdge.tile.index} at $newPosition")
        tileIndicesMap[secondEdge.tile.index] = newPosition
        log("${firstTile.index} ${firstEdge.originDirection} ${firstEdge.edge} is " +
                "${secondEdge.tile.index} ${secondEdge.originDirection} ")

        println("before transform")
        secondEdge.tile.rows.forEach { println(it) }
        val candidateEdges = buildNextCandidateEdge(firstEdge.originDirection, secondEdge, indexTilesMap, newPosition)
        candidateEdges.forEach {
            log("add tile ${it.tile.index} edge ${it.edge} " +
                    "direction ${it.originDirection}")
        }
        if (candidateEdges.isNotEmpty()) {
            indexTilesMap[newPosition] = candidateEdges.first().tile
            println("after transform")
            candidateEdges.first().tile.rows.forEach { println(it) }
        }
        edgesList.addAll(candidateEdges)
    }
}

fun buildNextCandidateEdge(
    firstEdgeDirection: EdgeDirection, secondEdge: TransformEdge,
    indicesTileMap: Map<Pair<Int, Int>, Tile>, secondTilePosition: Pair<Int, Int>,
): List<TransformEdge> {

    val secondEdgeNewDirection = when (firstEdgeDirection) {
        UNKNOWN -> TODO()
        TOP -> BOTTOM
        RIGHT -> LEFT
        BOTTOM -> TOP
        LEFT -> RIGHT
    }
    val indexDif = abs(secondEdgeNewDirection.index - secondEdge.originDirection.index)

    var rotatedRows = secondEdge.tile.rows
    if (firstEdgeDirection == secondEdge.originDirection) {
        rotatedRows = flipMatrix(rotatedRows, secondEdge.originDirection in listOf(TOP, BOTTOM))
    }
    val loopCount =
        if (indexDif == 2) {
            2
        } else {
            if (indexDif % 2 == 0) 1 else 0 + indexDif % 2
        }
    val needRotation = secondEdgeNewDirection != secondEdge.originDirection
    if (needRotation) {
        for (i in 0 until loopCount) {
            rotatedRows = rotateMatrix90(rotatedRows,
                secondEdgeNewDirection.index > secondEdge.originDirection.index)
        }
    }
    if (listOf(
            rotatedRows.first(),
            rotatedRows.map { it.last() }.joinToString(""),
            rotatedRows.last(),
            rotatedRows.map { it.first() }.joinToString(""),
        )[secondEdgeNewDirection.index - 1] != secondEdge.edge
    ) {
        rotatedRows = flipMatrix(rotatedRows, firstEdgeDirection in listOf(TOP, BOTTOM))
    }
    val edges = listOf(
        rotatedRows.first(),
        rotatedRows.map { it.last() }.joinToString(""),
        rotatedRows.last(),
        rotatedRows.map { it.first() }.joinToString(""),
    )

    val tilesPlacedStatus = listOf(
        firstEdgeDirection != BOTTOM &&
                indicesTileMap[Pair(secondTilePosition.first, secondTilePosition.second - 1)] == null,
        firstEdgeDirection != LEFT &&
                indicesTileMap[Pair(secondTilePosition.first + 1, secondTilePosition.second)] == null,
        firstEdgeDirection != TOP &&
                indicesTileMap[Pair(secondTilePosition.first, secondTilePosition.second + 1)] == null,
        firstEdgeDirection != RIGHT &&
                indicesTileMap[Pair(secondTilePosition.first - 1, secondTilePosition.second)] == null,
    )

    val edgesResult = mutableListOf<TransformEdge>()
    edges.forEachIndexed { i, it ->
        if (tilesPlacedStatus[i]) {
            val index = (i) % EdgeDirection.values().size + 1
            edgesResult.add(
                TransformEdge(
                    it,
                    Tile(secondEdge.tile.index, rotatedRows),
                    directionFromIndex(index),
                )
            )
        }
    }

    return edgesResult
}

fun findMonsters(matrix: Array<Array<Char>>, monsterPattern: MonsterPattern): Int {
    var monsterCount = 0
    val rightLimit = matrix.first().size - monsterPattern.rawRows.first().length
    val bottomLimit = matrix.size - monsterPattern.rawRows.size
    log("findMonster rightLimit $rightLimit bottomLimit $bottomLimit")
    for (i in 0..bottomLimit) {
        for (j in 0..rightLimit) {
            print("check i $i j $j ")
            if (!hasMonsterPattern(monsterPattern, i, j, matrix)) {
                println("does not have monster")
                continue
            }
            ++monsterCount
            println("has a monster")
            clearMonsterPattern(monsterPattern, i, j, matrix)
        }
    }
    return monsterCount
}
