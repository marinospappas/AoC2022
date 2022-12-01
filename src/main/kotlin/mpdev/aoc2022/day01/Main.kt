package mpdev.aoc2022.day01

import kotlin.system.measureTimeMillis

class Input(var inputList: MutableList<Elf>)

fun main(args: Array<String>) {

    val part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val input = getInput(args)
    val elapsedTime = measureTimeMillis {
        if (part1_2 == 1) {
            val result = solvePart1(input)
            println("$RESULT_STRING: ${result.res}")
        } else {
            val result2 = solvePart2(input)
            println("$RESULT_STRING2: ${result2.res}")
        }
    }
    exit("$DAY Part $part1_2 - Completed in $elapsedTime msec")
}