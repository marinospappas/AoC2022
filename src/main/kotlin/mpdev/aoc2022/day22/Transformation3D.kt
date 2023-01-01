package mpdev.aoc2022.day22

import mpdev.aoc2022.common.RunTimeException
import mpdev.aoc2022.common.testMode
import mpdev.aoc2022.day22.Day22.Position
import java.awt.Point

object Transformation3D {

    val newEdge = mutableMapOf<Position, Position>()
    private val transformations = if (testMode) listOf(
        Transformation(Pair(Line(Point(7,4),Point(4,4)), Line(Point(8,3), Point(8,0)))),      // 1 <-> 3
        Transformation(Pair(Line(Point(8,0), Point(11,0)), Line(Point(3,4),Point(0,4)))),     // 1 <-> 2
        Transformation(Pair(Line(Point(4,7),Point(7,7)), Line(Point(8,11), Point(8,8)))),     // 3 <-> 5
        Transformation(Pair(Line(Point(3,7),Point(0,7)), Line(Point(8,11), Point(11,11)))),   // 2 <-> 5
        Transformation(Pair(Line(Point(11,7),Point(11,4)), Line(Point(12,8), Point(15,8)))),  // 4 <-> 6
        Transformation(Pair(Line(Point(15,11), Point(15,8)), Line(Point(11,0),Point(11,3)))), // 1 <-> 6
        Transformation(Pair(Line(Point(0,4),Point(0,7)), Line(Point(15,11), Point(12,11)))),  // 2 <-> 6
    )
    else listOf(
        Transformation(Pair(Line(Point(100,49),Point(149,49)), Line(Point(99,50), Point(99,99)))),  // 1 <-> 3
        Transformation(Pair(Line(Point(149,49), Point(149,0)), Line(Point(99,100),Point(99,149)))), // 1 <-> 4
        Transformation(Pair(Line(Point(49,100),Point(0,100)), Line(Point(50,99), Point(50,50)))),   // 3 <-> 5
        Transformation(Pair(Line(Point(0,100), Point(0,149)), Line(Point(50,49),Point(50,0)))),    // 2 <-> 5
        Transformation(Pair(Line(Point(99,0),Point(50,0)), Line(Point(0,199), Point(0,150)))),      // 2 <-> 6
        Transformation(Pair(Line(Point(100,0), Point(149,0)), Line(Point(0,199),Point(49,199)))),   // 1 <-> 6
        Transformation(Pair(Line(Point(50,149),Point(99,149)), Line(Point(49,150), Point(49,199)))),// 4 <-> 6
    )

    fun initTransformations() {
        transformations.forEach { it.createTransformations() }
    }

    fun getNextPosition3D(position: Position) = newEdge[position] ?:
            throw RunTimeException("could not determine new position for $position")

    data class Line(val start:Point, val end:Point)

    class Transformation(private val edges: Pair<Line,Line>) {

        private val dirFirst = getDirection(edges.first)
        private val dirSecond = getDirection(edges.second)
        private val facingOut = getFacing(edges).first
        private val facingIn = getFacing(edges).second

        fun createTransformations() {

            if (dirFirst == UP && dirSecond == UP) {
                for (i in 0 .. (edges.first.start.y - edges.first.end.y)) {
                    newEdge[Position(edges.first.start.x, edges.first.start.y-i, facingOut)] =
                        Position(edges.second.start.x, edges.second.start.y-i, facingIn)
                    newEdge[Position(edges.second.start.x, edges.second.start.y-i, oppositeDir(facingIn))] =
                        Position(edges.first.start.x, edges.first.start.y-i, oppositeDir(facingOut))
                }
            }
            if (dirFirst == UP && dirSecond == DOWN) {
                for (i in 0 .. (edges.first.start.y - edges.first.end.y)) {
                    newEdge[Position(edges.first.start.x, edges.first.start.y-i, facingOut)] =
                        Position(edges.second.start.x, edges.second.start.y+i, facingIn)
                    newEdge[Position(edges.second.start.x, edges.second.start.y+i, oppositeDir(facingIn))] =
                        Position(edges.first.start.x, edges.first.start.y-i, oppositeDir(facingOut))
                }
            }
            if (dirFirst == DOWN && dirSecond == UP) {
                for (i in 0 .. (edges.first.end.y - edges.first.start.y)) {
                    newEdge[Position(edges.first.start.x, edges.first.start.y+i, facingOut)] =
                        Position(edges.second.start.x, edges.second.start.y-i, facingIn)
                    newEdge[Position(edges.second.start.x, edges.second.start.y-i, oppositeDir(facingIn))] =
                        Position(edges.first.start.x, edges.first.start.y+i, oppositeDir(facingOut))
                }
            }
            if (dirFirst == DOWN && dirSecond == DOWN) {
                for (i in 0 .. (edges.first.end.y - edges.first.start.y)) {
                    newEdge[Position(edges.first.start.x, edges.first.start.y+i, facingOut)] =
                        Position(edges.second.start.x, edges.second.start.y+i, facingIn)
                    newEdge[Position(edges.second.start.x, edges.second.start.y+i, oppositeDir(facingIn))] =
                        Position(edges.first.start.x, edges.first.start.y+i, oppositeDir(facingOut))
                }
            }

            if (dirFirst == RIGHT && dirSecond == RIGHT) {
                for (i in 0 .. (edges.first.end.x - edges.first.start.x)) {
                    newEdge[Position(edges.first.start.x+i, edges.first.start.y, facingOut)] =
                        Position(edges.second.start.x+i, edges.second.start.y, facingIn)
                    newEdge[Position(edges.second.start.x+i, edges.second.start.y, oppositeDir(facingIn))] =
                        Position(edges.first.start.x+i, edges.first.start.y, oppositeDir(facingOut))
                }
            }
            if (dirFirst == RIGHT && dirSecond == LEFT) {
                for (i in 0 .. (edges.first.end.x - edges.first.start.x)) {
                    newEdge[Position(edges.first.start.x+i, edges.first.start.y, facingOut)] =
                        Position(edges.second.start.x-i, edges.second.start.y, facingIn)
                    newEdge[Position(edges.second.start.x-i, edges.second.start.y, oppositeDir(facingIn))] =
                        Position(edges.first.start.x+i, edges.first.start.y, oppositeDir(facingOut))
                }
            }
            if (dirFirst == LEFT && dirSecond == RIGHT) {
                for (i in 0 .. (edges.first.start.x - edges.first.end.x)) {
                    newEdge[Position(edges.first.start.x-i, edges.first.start.y, facingOut)] =
                        Position(edges.second.start.x+i, edges.second.start.y, facingIn)
                    newEdge[Position(edges.second.start.x+i, edges.second.start.y, oppositeDir(facingIn))] =
                        Position(edges.first.start.x-i, edges.first.start.y, oppositeDir(facingOut))
                }
            }
            if (dirFirst == LEFT && dirSecond == LEFT) {
                for (i in 0 .. (edges.first.start.x - edges.first.end.x)) {
                    newEdge[Position(edges.first.start.x-i, edges.first.start.y, facingOut)] =
                        Position(edges.second.start.x-i, edges.second.start.y, facingIn)
                    newEdge[Position(edges.second.start.x-i, edges.second.start.y, oppositeDir(facingIn))] =
                        Position(edges.first.start.x-i, edges.first.start.y, oppositeDir(facingOut))
                }
            }

            if (dirFirst == RIGHT && dirSecond == UP) {
                for (i in 0 .. (edges.first.end.x - edges.first.start.x)) {
                    newEdge[Position(edges.first.start.x+i, edges.first.start.y, facingOut)] =
                        Position(edges.second.start.x, edges.second.start.y-i, facingIn)
                    newEdge[Position(edges.second.start.x, edges.second.start.y-i, oppositeDir(facingIn))] =
                        Position(edges.first.start.x+i, edges.first.start.y, oppositeDir(facingOut))
                }
            }
            if (dirFirst == RIGHT && dirSecond == DOWN) {
                for (i in 0 .. (edges.first.end.x - edges.first.start.x)) {
                    newEdge[Position(edges.first.start.x+i, edges.first.start.y, facingOut)] =
                        Position(edges.second.start.x, edges.second.start.y+i, facingIn)
                    newEdge[Position(edges.second.start.x, edges.second.start.y+i, oppositeDir(facingIn))] =
                        Position(edges.first.start.x+i, edges.first.start.y, oppositeDir(facingOut))
                }
            }
            if (dirFirst == LEFT && dirSecond == UP) {
                for (i in 0 .. (edges.first.start.x - edges.first.end.x)) {
                    newEdge[Position(edges.first.start.x-i, edges.first.start.y, facingOut)] =
                        Position(edges.second.start.x, edges.second.start.y-i, facingIn)
                    newEdge[Position(edges.second.start.x, edges.second.start.y-i, oppositeDir(facingIn))] =
                        Position(edges.first.start.x-i, edges.first.start.y, oppositeDir(facingOut))
                }
            }
            if (dirFirst == LEFT && dirSecond == DOWN) {
                for (i in 0 .. (edges.first.start.x - edges.first.end.x)) {
                    newEdge[Position(edges.first.start.x-i, edges.first.start.y, facingOut)] =
                        Position(edges.second.start.x, edges.second.start.y+i, facingIn)
                    newEdge[Position(edges.second.start.x, edges.second.start.y+i, oppositeDir(facingIn))] =
                        Position(edges.first.start.x-i, edges.first.start.y, oppositeDir(facingOut))
                }
            }
            if (dirFirst == UP && dirSecond == RIGHT) {
                for (i in 0 .. (edges.first.start.y - edges.first.end.y)) {
                    newEdge[Position(edges.first.start.x, edges.first.start.y-i, facingOut)] =
                        Position(edges.second.start.x+i, edges.second.start.y, facingIn)
                    newEdge[Position(edges.second.start.x+i, edges.second.start.y, oppositeDir(facingIn))] =
                        Position(edges.first.start.x, edges.first.start.y-i, oppositeDir(facingOut))
                }
            }
            if (dirFirst == UP && dirSecond == LEFT) {
                for (i in 0 .. (edges.first.start.y - edges.first.end.y)) {
                    newEdge[Position(edges.first.start.x, edges.first.start.y-i, facingOut)] =
                        Position(edges.second.start.x-i, edges.second.start.y, facingIn)
                    newEdge[Position(edges.second.start.x-i, edges.second.start.y, oppositeDir(facingIn))] =
                        Position(edges.first.start.x, edges.first.start.y-i, oppositeDir(facingOut))
                }
            }
            if (dirFirst == DOWN && dirSecond == RIGHT) {
                for (i in 0 .. (edges.first.end.y - edges.first.start.y)) {
                    newEdge[Position(edges.first.start.x, edges.first.start.y+i, facingOut)] =
                        Position(edges.second.start.x+i, edges.second.start.y, facingIn)
                    newEdge[Position(edges.second.start.x+i, edges.second.start.y, oppositeDir(facingIn))] =
                        Position(edges.first.start.x, edges.first.start.y+i, oppositeDir(facingOut))
                }
            }
            if (dirFirst == DOWN && dirSecond == LEFT) {
                for (i in 0 .. (edges.first.end.y - edges.first.start.y)) {
                    newEdge[Position(edges.first.start.x, edges.first.start.y+i, facingOut)] =
                        Position(edges.second.start.x-i, edges.second.start.y, facingIn)
                    newEdge[Position(edges.second.start.x-i, edges.second.start.y, oppositeDir(facingIn))] =
                        Position(edges.first.start.x, edges.first.start.y+i, oppositeDir(facingOut))
                }
            }
        }

        private fun getDirection(line: Line): Int {
            if (line.end.y == line.start.y) {
                if (line.end.x > line.start.x)
                    return RIGHT
                else
                    return LEFT
            }
            if (line.end.x == line.start.x) {
                if (line.end.y > line.start.y)
                    return DOWN
                else
                    return UP
            }
            throw RunTimeException("invalid edge coordinates ${line.start} - ${line.end}")
        }

        private fun getFacing(edges: Pair<Line,Line>): Pair<Int,Int> {
            // edges parallel
            if (dirFirst == UP && dirSecond == DOWN
                || dirFirst == DOWN && dirSecond == UP) {
                if (edges.first.start.x < edges.second.start.x)
                    return Pair(LEFT,RIGHT)
                else
                    return Pair(RIGHT,LEFT)
            }
            if (dirFirst == UP && dirSecond == UP
                || dirFirst == DOWN && dirSecond == DOWN) {
                if (edges.first.start.x < edges.second.start.x)
                    return Pair(LEFT,LEFT)
                else
                    return Pair(RIGHT,RIGHT)
            }
            if (dirFirst == LEFT && dirSecond == RIGHT
                || dirFirst == RIGHT && dirSecond == LEFT) {
                if (edges.first.start.y < edges.second.start.y)
                    return Pair(DOWN,UP)
                else
                    return Pair(UP,DOWN)
            }
            if (dirFirst == LEFT && dirSecond == LEFT
                || dirFirst == RIGHT && dirSecond == RIGHT) {
                if (edges.first.start.y < edges.second.start.y)
                    return Pair(UP,UP)
                else
                    return Pair(DOWN,DOWN)
            }
            // edges perpendicular
            if (dirFirst == LEFT && dirSecond == UP) {
                return Pair(UP,RIGHT)
            }
            if (dirFirst == RIGHT && dirSecond == UP) {
                return Pair(UP,LEFT)
            }
            if (dirFirst == LEFT && dirSecond == DOWN) {
                return Pair(DOWN,RIGHT)
            }
            if (dirFirst == RIGHT && dirSecond == DOWN) {
                return Pair(DOWN,LEFT)
            }
            if (dirFirst == UP && dirSecond == LEFT) {
                return Pair(LEFT,DOWN)
            }
            if (dirFirst == DOWN && dirSecond == LEFT) {
                return Pair(LEFT,UP)
            }
            if (dirFirst == UP && dirSecond == RIGHT) {
                return Pair(RIGHT,DOWN)
            }
            if (dirFirst == DOWN && dirSecond == RIGHT) {
                return Pair(RIGHT,UP)
            }
            throw RunTimeException("could not determine facing ${edges.first} - ${edges.second}")
        }

        private fun oppositeDir(dir: Int) = when (dir) {
            UP -> DOWN
            LEFT -> RIGHT
            DOWN -> UP
            RIGHT -> LEFT
            else -> throw RunTimeException("invalid direction $dir")
        }
    }
}