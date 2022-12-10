package mpdev.aoc2022.day10

import mpdev.aoc2022.common.*

class SolutionProcessorDay10: SolutionProcessor<InputDay10> {

    /** part 1 calculation */
    override fun part1(input: InputDay10): String {
        input.crt.scanScreen(input.instrList)
        return mutableListOf<Int>().also { list -> (0..5)
            .forEach { i -> list.add(input.crt.scanner[40*i+20] * (40*i+20)) } }.sum().toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay10): String {
        input.crt.scanScreen(input.instrList)
        input.crt.draw()
        input.crt.screen.forEach { line -> println( line.joinToString("") ) }
        return ""
    }
}