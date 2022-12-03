package mpdev.aoc2022.common

import java.io.File
import javax.print.DocFlavor.INPUT_STREAM
import kotlin.system.measureTimeMillis

class PuzzleSolution<T> (var part1_2: Int,
                         var day: Int,
                         var filename: String,
                         var inputLine: InputLine<T>,
                         var constants: Constants,
                         var solution: Solution<T>) {

    /** get puzzle input */
    fun getInput(filename: String): Input<T> {
        val inputData = mutableListOf<T>()
        println("input file: $filename")
        var firstLine = true
        try {
            File(filename).forEachLine {
                inputLine.process(firstLine, it, inputData)
                firstLine = false
            }
        }
        catch (e: Exception) {
            abort(e.toString())
        }
        return Input(inputData)
    }

    fun process() {
        println("${Constants.AOC} - Day $day, ${constants.PUZZLE}, Part $part1_2 - ${Constants.AUTHOR} - ${constants.DATE}")
        val input = getInput(filename)
        val elapsedTime = measureTimeMillis {
            if (part1_2 == 1) {
                val result = solution.part1(input)
                println("${constants.RESULT_STRING}: ${result.res}")
            } else {
                val result2 = solution.part2(input)
                println("${constants.RESULT_STRING2}: ${result2.res}")
            }
        }
        exit("Day $day Part $part1_2 - Completed in $elapsedTime msec")
    }

}

