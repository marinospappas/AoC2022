package mpdev.aoc2022.day23

import mpdev.aoc2022.common.*

class SolutionProcessorDay23: SolutionProcessor<InputDay23> {

    /** part 1 calculation */
    override fun part1(input: InputDay23): String {
        (1..10).forEach { input.playRound() }
        return input.getResult().toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay23): String {
        var rounds = 1
        while(input.playRound() > 0)
            ++rounds
        return rounds.toString()
    }

}