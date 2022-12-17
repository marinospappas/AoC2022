package mpdev.aoc2022.day17

import mpdev.aoc2022.common.*
import mpdev.aoc2022.utils.Dijkstra
import mpdev.aoc2022.utils.permutations

class SolutionProcessorDay17: SolutionProcessor<InputDay17> {

    /** part 1 calculation */
    override fun part1(input: InputDay17): String {
        val res = input.playTetris(2022)
        println(input.gridToString(40))
        return res.toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay17): String {
        return ""
    }

}