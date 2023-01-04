package mpdev.aoc2022.day25

import mpdev.aoc2022.common.*
import mpdev.aoc2022.day25.Day25.Companion.snafuList

class SolutionProcessorDay25: SolutionProcessor<Day25> {

    /** part 1 calculation */
    override fun part1(input: Day25): String {
        val intRes = snafuList.sumOf { it.toLong() }
        println("int result: $intRes")
        return intRes.toSnafu()
    }

    /** part 2 calculation */
    override fun part2(input: Day25): String {
        return ""
    }

}