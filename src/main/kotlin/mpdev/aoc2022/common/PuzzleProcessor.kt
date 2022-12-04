package mpdev.aoc2022.common

import java.io.File
import kotlin.system.measureTimeMillis

class PuzzleProcessor<T> (var part1Or2: Int,
                          var day: Int,
                          var constants: Constants,
                          var input: List<String>,
                          var inputProcessor: InputProcessor<T>,
                          var solution: SolutionProcessor<T, Input<T>>) {

    /** get puzzle input */

    fun process(): Int? {
        println("${Constants.AOC} - Day $day, ${constants.PUZZLE}, Part $part1Or2 - ${Constants.AUTHOR} - ${constants.DATE}")
        val inputData = inputProcessor.process(input)
        if (part1Or2 == 1) {
            val result = solution.part1(inputData)
            println("${constants.RESULT_STRING}: ${result.res}")
            return result.res
        }
        if (part1Or2 == 2) {
            val result2 = solution.part2(inputData)
            println("${constants.RESULT_STRING2}: ${result2.res}")
            return result2.res
        }
        return null
    }

}

