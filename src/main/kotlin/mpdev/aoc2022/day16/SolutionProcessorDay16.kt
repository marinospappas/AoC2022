package mpdev.aoc2022.day16

import mpdev.aoc2022.common.*

class SolutionProcessorDay16: SolutionProcessor<Day16> {

    /** part 1 calculation */
    override fun part1(input: Day16): String {
        input.calculateMinPathCombinations()
        return input.calculateMaxPressureRelief1(30).toString()
    }


    override fun part2(input: Day16): String {
        return ""
    }

}