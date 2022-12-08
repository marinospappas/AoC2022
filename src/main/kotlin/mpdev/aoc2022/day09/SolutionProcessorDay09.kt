package mpdev.aoc2022.day09

import mpdev.aoc2022.common.*

class SolutionProcessorDay09: SolutionProcessor<InputDay09> {

    /** part 1 calculation */
    override fun part1(input: InputDay09): String {
        var totalVisible = 0
        (0..input.numOfRows-1).forEach { row -> (0..input.numOfColumns-1).forEach { col ->
            if (input.isVisible(row,col)) ++totalVisible } }

        println ( (0..input.numOfRows-1).forEach { row ->
            (0..input.numOfColumns - 1).forEach { col ->
                input.isVisible(row, col)
            } }.toString())
        return totalVisible.toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay09): String {
        return mutableListOf<Int>().also {
                (1..input.numOfRows - 2).forEach { row -> (1..input.numOfColumns - 2).forEach { col ->
                        it.add(input.scenicScore(row, col)) } }
            }.max().toString()
    }
}