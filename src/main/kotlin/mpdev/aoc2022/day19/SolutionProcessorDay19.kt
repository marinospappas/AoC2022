package mpdev.aoc2022.day19

import mpdev.aoc2022.common.*

class SolutionProcessorDay19: SolutionProcessor<Day19> {

    /** part 1 calculation */
    override fun part1(day19: Day19): String {
        return day19.blueprintList.sumOf { it.id * day19.calculateGeodes(it, day19.maxTime) }.toString()
    }

    /** part 2 calculation */
    override fun part2(input: Day19): String {
        return ""
    }

}