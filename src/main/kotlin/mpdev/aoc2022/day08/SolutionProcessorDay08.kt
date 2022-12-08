package mpdev.aoc2022.day08

import mpdev.aoc2022.common.*

class SolutionProcessorDay08: SolutionProcessor<InputDay08> {

    /** part 1 calculation */
    override fun part1(input: InputDay08): String {
        var totalVisible = 0
        (0..input.numOfRows-1).forEach { row -> (0..input.numOfColumns-1).forEach { col ->
            if (input.isVisible(row,col)) ++totalVisible } }

        println (
            (0..input.numOfRows - 1).forEach { row ->
                (0..input.numOfColumns - 1).filter { col ->
                    input.isVisible(row, col)
                }.count()
            }.toString())
        return totalVisible.toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay08): String {
        return mutableListOf<Int>().also {
                (1..input.numOfRows - 2).forEach { row -> (1..input.numOfColumns - 2).forEach { col ->
                        it.add(input.scenicScore(row, col)) } }
            }.max().toString()
    }
}