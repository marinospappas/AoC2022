package mpdev.aoc2022.day09

import java.awt.Point

object MovesMap {
    val entry: Map<Point,Point> = mapOf(
        Point(0, 0) to Point(0, 0),         // adjacent
        Point(1, 0) to Point(0, 0),
        Point(-1, 0) to Point(0, 0),
        Point(0, 1) to Point(0, 0),
        Point(0, -1) to Point(0, 0),
        Point(1, 1) to Point(0, 0),
        Point(1, -1) to Point(0, 0),
        Point(-1, 1) to Point(0, 0),
        Point(-1, -1) to Point(0, 0),

        Point(2, 0) to Point(1, 0),         // up or down
        Point(-2, 0) to Point(-1, 0),

        Point(0, 2) to Point(0, 1),         // left or right
        Point(0, -2) to Point(0, -1),

        Point(2, 1) to Point(1, 1),         // diagonally
        Point(1, 2) to Point(1, 1),
        Point(2, 2) to Point(1, 1),
        Point(2, -1) to Point(1, -1),
        Point(1, -2) to Point(1, -1),
        Point(2, -2) to Point(1, -1),
        Point(-2, 1) to Point(-1, 1),
        Point(-1, 2) to Point(-1, 1),
        Point(-2, 2) to Point(-1, 1),
        Point(-2, -1) to Point(-1, -1),
        Point(-1, -2) to Point(-1, -1),
        Point(-2, -2) to Point(-1, -1),
    )
}