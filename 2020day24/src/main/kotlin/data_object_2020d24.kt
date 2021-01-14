package main.kotlin

import main.kotlin.Direction.*

enum class Direction(val abbr: String) {
    UNKNOWN(""),
    EAST("e"),
    SOUTH_EAST("se"),
    SOUTH_WEST("sw"),
    WEST("w"),
    NORTH_WEST("nw"),
    NORTH_EAST("ne"),
}

fun getDirectionFromAbbr(abbr: String): Direction {
    return when (abbr) {
        "se" -> SOUTH_EAST
        "sw" -> SOUTH_WEST
        "nw" -> NORTH_WEST
        "ne" -> NORTH_EAST
        else -> {
            when (abbr[0]) {
                'e' -> EAST
                'w' -> WEST
                else -> UNKNOWN
            }
        }
    }
}

fun getCoordinateMovement(direction: Direction): Pair<Double, Double> {
    return when (direction) {
        UNKNOWN -> TODO()
        EAST -> Pair(1.0, 0.0)
        SOUTH_EAST -> Pair(0.5, -1.0)
        SOUTH_WEST -> Pair(-0.5, -1.0)
        WEST -> Pair(-1.0, 0.0)
        NORTH_WEST -> Pair(-0.5, 1.0)
        NORTH_EAST -> Pair(0.5, 1.0)
    }
}

val AdjacentCoordinates = listOf(
    Pair(1.0, 0.0),
    Pair(0.5, -1.0),
    Pair(-0.5, -1.0),
    Pair(-1.0, 0.0),
    Pair(-0.5, 1.0),
    Pair(0.5, 1.0),
)
