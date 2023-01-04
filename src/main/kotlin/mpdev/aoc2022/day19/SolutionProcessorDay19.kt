package mpdev.aoc2022.day19

import mpdev.aoc2022.common.*

class SolutionProcessorDay19: SolutionProcessor<Day19> {

    /** part 1 calculation */
    override fun part1(input: Day19): String {
        return input.blueprintList.sumOf { it.id * input.calculateGeodes(it, 24) }.toString()
    }

    /** part 2 calculation */
    override fun part2(input: Day19): String {
        return input.blueprintList.take(3).map { input.calculateGeodes(it, 32) }.reduce { acc, i -> acc * i }.toString()
    }
}