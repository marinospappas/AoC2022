package mpdev.aoc2022.day08

import mpdev.aoc2022.common.*

class SolutionProcessorDay08: SolutionProcessor<InputDay08> {

    /** part 1 calculation */
    override fun part1(input: InputDay08): String {
        var totalVisible = 0
        for (i in 0..input.numOfRows-1)
            for (j in 0..input.numOfColumns-1)
                if (input.isVisible(i,j))
                    ++totalVisible
        return totalVisible.toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay08): String {
        var score = 0
        for (i in 1..input.numOfRows-2)
            for (j in 1..input.numOfColumns-2) {
                val thisScore = input.scenicScore(i, j)
                if (thisScore > score)
                    score = thisScore
            }
        return score.toString()
    }
}