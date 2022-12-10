package mpdev.aoc2022.day09

import mpdev.aoc2022.common.RunTimeException
import mpdev.aoc2022.common.testMode
import mpdev.aoc2022.utils.Grid
import mpdev.aoc2022.utils.isAdjacent
import mpdev.aoc2022.utils.plus
import mpdev.aoc2022.utils.minus

class InputDay09(var rope: Rope, var moves: List<Pair<Char,Int>>) {

    var grid = Grid()
    val tailTrail = mutableListOf(Pair(0, 0))

    /** executes given moves and returns tail trail */
    fun executeMoves(): List<Pair<Int, Int>> {
        moves.forEach {
            rope.executeMove(it, tailTrail)
            if (testMode) { grid = Grid(rope.knots, 'r'); println("== move $it ==\n$grid") }
        }
        if (testMode) { grid = Grid(tailTrail, 't'); println("== tail trail ==\n$grid") }
        return tailTrail
    }
}

/** rope class */
class Rope(var knots: MutableList<Pair<Int,Int>>) {

    /** execute a move */
    fun executeMove(move: Pair<Char,Int>, tailTrail: MutableList<Pair<Int,Int>>) {
        (1..move.second).forEach { _ -> tailTrail.add( moveRope1Step( getFirstMove(move) ))  }
    }

    private fun moveRope1Step(move: Pair<Int,Int>):  Pair<Int,Int> {
        knots[0] = knots[0].plus(move)      // move head
        if (knots.lastIndex > 0) {          // move rest of the rope recursively
            val nextMove = getNextMove(knots[0], knots[1], 0)
                ?: throw RunTimeException("could not find tail move for head pos ${knots[0]} tail pos ${knots[1]}")
                                            // would work without this check as move by 0 would do nothing
            if (nextMove != Pair(0,0))      // however this reduces exec time by 2 for a 10-knot rope and by 10 for a 250-knot rope
                Rope(knots.subList(1, knots.lastIndex+1)).moveRope1Step(nextMove)
        }
        return knots.last()     // returns the position of tail
    }

    private fun getNextMove(knot1: Pair<Int,Int>, knot2: Pair<Int,Int>, mode: Int): Pair<Int,Int>? {
        return when (mode) {
            0 -> MovesMap.entry[knot1.minus(knot2)]       // map lookup
            1 -> if (knot1.isAdjacent(knot2))
                    Pair(0,0)
                else
                    Pair(                           // arithmetic formula - no difference in execution time
                        knot1.first-knot2.first - (knot1.first-knot2.first) / 2,
                        knot1.second-knot2.second - (knot1.second-knot2.second) / 2
                    )
            else-> null
        }
    }

    private fun getFirstMove(move: Pair<Char,Int>): Pair<Int,Int> =
        when (move.first) {
            'U' -> Pair(0, +1)
            'D' -> Pair(0, -1)
            'L' -> Pair(-1, 0)
            'R' -> Pair(+1, 0)
            else-> Pair(0,  0)
        }
}

