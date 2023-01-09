package mpdev.aoc2022.day09

import mpdev.aoc2022.common.RunTimeException
import mpdev.aoc2022.common.animationObject
import mpdev.aoc2022.common.testMode
import mpdev.aoc2022.utils.Grid
import mpdev.aoc2022.utils.annimation.SHAPE_CIRCLE
import mpdev.aoc2022.utils.isAdjacent
import mpdev.aoc2022.utils.plus
import mpdev.aoc2022.utils.minus
import java.awt.Color
import java.awt.Point

val animationShift = Point(12, 6)
const val animationYShift = 22
val animationTrail = mutableListOf(Point(0, 0))

class Day09(var rope: Rope, var moves: List<Pair<Char,Int>>) {

    var grid = Grid()
    val tailTrail = mutableListOf(Point(0, 0))
    var animationRope: Rope

    init {
        animationObject.addItem()
        animationObject.addItem()
        animationRope = Rope(
            mutableListOf<Point>().also { list -> (1..10).forEach { list.add(Point(0,0)) } }
        )
    }

    /** executes given moves and returns tail trail */
    fun executeMoves(): List<Point> {
        moves.forEach {
            rope.executeOneMove(it, tailTrail)
            if (testMode) { grid = Grid(rope.knots, 'r'); println("== move $it ==\n$grid") }
        }
        if (testMode) { grid = Grid(tailTrail, 't'); println("== tail trail ==\n$grid") }
        return tailTrail
    }

    /** animation */
    fun animateMoves() {
        animationRope.animateRope()
        moves.forEach { animationRope.animateOneMove(it) }
    }
}

/** rope class */
class Rope(var knots: MutableList<Point>) {

    /** execute one move */
    fun executeOneMove(move: Pair<Char,Int>, tailTrail: MutableList<Point>) {
        (1..move.second).forEach { _ -> tailTrail.add( moveRopeOneStep( getFirstMove(move), tailTrail )) }
    }

    /** move the rope one step */
    private fun moveRopeOneStep(move: Point, tailTrail: List<Point>):  Point {
        knots[0] = knots[0] + move          // move head
        if (knots.lastIndex > 0) {          // move rest of the rope recursively
            val nextMove = getNextMove(knots[0], knots[1], 0)
            // would work without this check as move by 0 would do nothing
            // however this reduces exec time by 2 for a 10-knot rope and by 10 for a 250-knot rope
            if (nextMove != Point(0,0))
                Rope(knots.subList(1, knots.lastIndex+1)).moveRopeOneStep(nextMove, tailTrail)
        }
        return knots.last()     // returns the position of tail
    }

    private fun getNextMove(knot1: Point, knot2: Point, mode: Int): Point {
        return when (mode) {
            0 -> MovesMap.entry[knot1 - knot2]   // map lookup
                ?: throw RunTimeException("could not find tail move for pos1 $knot1 pos2 $knot2")
            1 -> if (knot1.isAdjacent(knot2))
                    Point(0,0)
                else
                    Point(                       // arithmetic formula - no difference in execution time
                        knot1.x-knot2.x - (knot1.x-knot2.x) / 2,
                        knot1.y-knot2.y - (knot1.y-knot2.y) / 2
                    )
            else-> throw RunTimeException("could not find tail move for pos1 $knot1 pos2 $knot2")
        }
    }

    private fun getFirstMove(move: Pair<Char,Int>): Point =
        when (move.first) {
            'U' -> Point(0, +1)
            'D' -> Point(0, -1)
            'L' -> Point(-1, 0)
            'R' -> Point(+1, 0)
            else-> Point(0,  0)
        }

    /////////// animation
    /** animate one move */
    fun animateOneMove(move: Pair<Char,Int>) {
        (1..move.second).forEach { _ -> animationTrail.add(animateRopeMoveOneStep( getFirstMove(move) )) }
    }
    /** animate rope movement one step */
    private fun animateRopeMoveOneStep(move: Point): Point {
        var nextMove = move
        for (i in knots.indices) {
            animateRope()
            knots[i] = knots[i] + nextMove
            if (i < knots.lastIndex) nextMove = getNextMove(knots[i], knots[i+1], 0)
        }
        animateRope()
        return knots.last()
    }
    fun animateRope() {
        animationObject.addItem()
        for (i in knots.lastIndex downTo 0) {
            val color = when (i) {
                0 -> Color.RED
                knots.lastIndex -> Color.BLUE
                else -> Color.YELLOW
            }
            var pixelPos = Point((knots[i]+animationShift).x, animationYShift - (knots[i]+animationShift).y)
            animationObject.addPixel(pixelPos, SHAPE_CIRCLE, color)
            animationTrail.forEach {
                pixelPos = Point((it+animationShift).x, animationYShift - (it+animationShift).y)
                animationObject.addPixel(pixelPos, SHAPE_CIRCLE, Color.BLUE)

            }
        }
    }
}
