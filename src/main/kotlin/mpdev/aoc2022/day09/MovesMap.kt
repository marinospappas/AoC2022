package mpdev.aoc2022.day09

object MovesMap {
    val entry = mapOf(
        Pair(0, 0) to Pair(0, 0),         // adjacent
        Pair(1, 0) to Pair(0, 0),
        Pair(-1, 0) to Pair(0, 0),
        Pair(0, 1) to Pair(0, 0),
        Pair(0, -1) to Pair(0, 0),
        Pair(1, 1) to Pair(0, 0),
        Pair(1, -1) to Pair(0, 0),
        Pair(-1, 1) to Pair(0, 0),
        Pair(-1, -1) to Pair(0, 0),

        Pair(2, 0) to Pair(1, 0),         // up or down
        Pair(-2, 0) to Pair(-1, 0),

        Pair(0, 2) to Pair(0, 1),         // left or right
        Pair(0, -2) to Pair(0, -1),

        Pair(2, 1) to Pair(1, 1),         // diagonally
        Pair(1, 2) to Pair(1, 1),
        Pair(2, 2) to Pair(1, 1),
        Pair(2, -1) to Pair(1, -1),
        Pair(1, -2) to Pair(1, -1),
        Pair(2, -2) to Pair(1, -1),
        Pair(-2, 1) to Pair(-1, 1),
        Pair(-1, 2) to Pair(-1, 1),
        Pair(-2, 2) to Pair(-1, 1),
        Pair(-2, -1) to Pair(-1, -1),
        Pair(-1, -2) to Pair(-1, -1),
        Pair(-2, -2) to Pair(-1, -1),
    )
}