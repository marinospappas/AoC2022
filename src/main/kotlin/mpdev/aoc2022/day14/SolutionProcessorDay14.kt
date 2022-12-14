package mpdev.aoc2022.day14

import mpdev.aoc2022.common.*

class SolutionProcessorDay14: SolutionProcessor<InputDay14> {

    /** part 1 calculation */
    override fun part1(input: InputDay14): String {
        while (input.dropOneGrain()) ;
        println(input.gridToString())
        return input.getGrainsCount().toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay14): String {
        input.extendGrid2()
        while (input.dropOneGrain2()) ;
        println(input.gridToString())
        return input.getGrainsCount().toString()
        return input.getGrainsCount().toString()
    }

}