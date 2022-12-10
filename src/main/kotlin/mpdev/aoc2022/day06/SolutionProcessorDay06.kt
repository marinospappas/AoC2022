package mpdev.aoc2022.day06

import mpdev.aoc2022.common.*
import mpdev.aoc2022.utils.allCharsDifferent

class SolutionProcessorDay06: SolutionProcessor<InputDay06> {

    /** find start of packet */
    private fun findStartOfPacket(input: String, startSeqLen: Int): Int {
        for (i in startSeqLen..input.length)
            if (input.substring(i-startSeqLen, i).allCharsDifferent())
                return i
        return 0
    }

    /** part 1 calculation */
    override fun part1(input: InputDay06) = findStartOfPacket(input.dataBuffer, 4).toString()

    /** part 2 calculation */
    override fun part2(input: InputDay06) = findStartOfPacket(input.dataBuffer, 14).toString()
}