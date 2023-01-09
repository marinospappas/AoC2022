package mpdev.aoc2022.day09

import mpdev.aoc2022.common.*
import java.awt.Point

class SolutionProcessorDay09: SolutionProcessor<Day09> {

    private fun setupAndMoveRope(puzzle: Day09, numKnots: Int): List<Point> {
        puzzle.rope = Rope(
            mutableListOf<Point>().also { list -> (1..numKnots).forEach { list.add(Point(0,0)) } }
        )
        return puzzle.executeMoves()
    }

    /** part 1 calculation */
    override fun part1(puzzle: Day09) = setupAndMoveRope(puzzle, 2).distinct().count().toString()

    /** part 2 calculation */
    override fun part2(puzzle: Day09) = setupAndMoveRope(puzzle, 10).distinct().count().toString()
}