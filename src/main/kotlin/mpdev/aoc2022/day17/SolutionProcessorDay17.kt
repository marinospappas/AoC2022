package mpdev.aoc2022.day17

import mpdev.aoc2022.common.*

class SolutionProcessorDay17: SolutionProcessor<Day17> {

    /** part 1 calculation */
    override fun part1(input: Day17): String {
        val res = input.playTetris(2022)
        println(input.gridToString(40))
        return res.toString()
    }

    /** part 2 calculation */
    override fun part2(input: Day17): String {
        val res = input.playTetris2("1000000000000")
        println(input.gridToString(40))
        return res
    }

}