package mpdev.aoc2022.day18

import mpdev.aoc2022.common.*

class SolutionProcessorDay18: SolutionProcessor<Day18> {

    /** part 1 calculation */
    override fun part1(input: Day18): String {
        return input.getExposedSurface().toString()
    }

    /** part 2 calculation */
    override fun part2(input: Day18): String {
        return input.getExposedSurface(true).toString()
    }
}