package mpdev.aoc2022.day20

import mpdev.aoc2022.common.*

class SolutionProcessorDay20: SolutionProcessor<Day20> {

    /** part 1 calculation */
    override fun part1(input: Day20): String {
        input.shiftList()
        println(input.shiftedList())
        return input.getCoordinatesSum().toString()
    }

    /** part 2 calculation */
    override fun part2(input: Day20): String {
        input.processDecrKey()
        (1..10).forEach { _ -> input.shiftList() }
        println(input.shiftedList())
        return input.getCoordinatesSum().toString()
    }

}