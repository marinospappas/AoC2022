package mpdev.aoc2022.day09

import mpdev.aoc2022.common.*

class SolutionProcessorDay09: SolutionProcessor<InputDay09> {

    private fun setupAndMoveRope(input: InputDay09, numKnots: Int): List<Pair<Int,Int>> {
        input.rope = Rope(mutableListOf<Pair<Int,Int>>().also { list -> (1..numKnots).forEach { list.add(Pair(0,0)) } })
        return input.executeMoves()
    }

    /** part 1 calculation */
    override fun part1(input: InputDay09): String {
        return setupAndMoveRope(input, 2).distinct().count().toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay09): String {
        return setupAndMoveRope(input, 10).distinct().count().toString()
    }
}