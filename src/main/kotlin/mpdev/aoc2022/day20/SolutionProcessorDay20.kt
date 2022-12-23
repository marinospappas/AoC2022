package mpdev.aoc2022.day20

import mpdev.aoc2022.common.*

class SolutionProcessorDay20: SolutionProcessor<InputDay20> {

    /** part 1 calculation */
    override fun part1(input: InputDay20): String {
        input.shiftList()
        input.encrList.sortedBy { it.position }.forEach {
            println("pos:${it.position}: ${it.value}")
        }
        return input.getCoordinatesSum().toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay20): String {
        return ""
    }

}