package mpdev.aoc2022.day16

import mpdev.aoc2022.common.*
import mpdev.aoc2022.utils.Dijkstra
import mpdev.aoc2022.utils.permutations

class SolutionProcessorDay16: SolutionProcessor<Day16> {

    /** part 1 calculation */
    override fun part1(input: Day16): String {
        input.findMinPathCombinations()
        return input.findMaxPressureRelief1(30).toString()
    }


    override fun part2(input: Day16): String {
        return ""
    }

}