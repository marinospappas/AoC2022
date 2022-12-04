package mpdev.aoc2022.day02

import mpdev.aoc2022.common.*

class SolutionProcessorDay02: SolutionProcessor<PlayData, Input<PlayData>> {

    /** part 1 calculation */
    override fun part1(input: Input<PlayData>): Result1 {
        return Result1(input.inputList.sumOf { rockPaperScissorsB(it.player2, it.player1) })

    }

    fun calculateStrategy(self: Char, opponent: Char): Char {
        val myStrategy = self.code - 'X'.code
        val otherSelection = opponent.code - 'A'.code
        val result: Int = when (myStrategy) {
            1 -> otherSelection     // draw
            2 -> (otherSelection + 1) % 3  // win
            else -> (otherSelection + 2) % 3  // lose
        }
        return (result+'X'.code).toChar()
    }

    /** part 2 calculation */
    override fun part2(input: Input<PlayData>): Result2 {
        return Result2(input.inputList.sumOf { rockPaperScissorsB(calculateStrategy(it.player2, it.player1), it.player1) })
    }
}