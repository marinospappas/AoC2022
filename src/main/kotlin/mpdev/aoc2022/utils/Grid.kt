package mpdev.aoc2022.utils

import mpdev.aoc2022.common.testMode
import java.awt.Point
import java.lang.StringBuilder

/** grid class - used only for visualisation */
class Grid(rope: MutableList<Point> = mutableListOf(Point(0,0)), mode: Char = 'r' ) {

    var dimensions = Point(6,5)
    private val gridData = mutableListOf<MutableList<Char>>()
    private val trail = mutableListOf<Point>()
    private var shift = Point(0,0)
    private var start = Point(0,0)

    init {
        //val minCoord = Point((rope+start).minOf { it.x }, (rope+start).minOf { it.y })
        //val maxCoord = Point((rope+start).maxOf { it.x }, (rope+start).maxOf { it.y })
        val minCoord = Point(-12, -6)
        val maxCoord = Point(14, 15)

        if (testMode)   println("X-range: ${minCoord.x} - ${maxCoord.x}  Y-range: ${minCoord.y} - ${maxCoord.y}")
        dimensions = Point(maxCoord.x-minCoord.x+2, maxCoord.y-minCoord.y+2)
        shift = Point(-minCoord.x, -minCoord.y)
        start = start.plus(shift)
        rope.forEach { trail.add(it.plus(shift)) }
        // grid data
        (dimensions.y-1 downTo 0).forEach { y ->
            mutableListOf<Char>().also {
                (0 until dimensions.x).forEach { x ->
                    if (mode == 'r')    // rope
                        when {
                            (Point(x, y) == trail[0]) -> it.add('H')
                            trail.indexOf(Point(x, y)) >= 0 -> it.add((trail.indexOf(Point(x, y))%10).toString().first())
                            Point(x, y) == start -> it.add('s')
                            else -> it.add('.')
                        }
                    else                // trail
                        when {
                            Point(x, y) == start -> it.add('s')
                            trail.indexOf(Point(x, y)) >= 0 -> it.add('#')
                            else -> it.add('.')
                        }
                }
                gridData.add(it)
            }
        }
    }
    override fun toString() =
        StringBuilder().also { s -> gridData.forEach { s.append(it.joinToString("")).append("\n") } }.toString()
}
