package mpdev.aoc2022.common

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    val part1Or2 = getPart1or2(args)
    val day = getDay(args)
    if (part1Or2 < 0)
        abort(Constants.USAGE)
    if (day !in 1..25)
        abort(Constants.USAGE)

    val filename = "src/main/resources/day${String.format("%02d", day)}/input.txt"
    val inputLines = getInput(filename)
    val puzzle = DaySpecific.getProcessor(part1Or2, day, inputLines)
    if (puzzle == null)
        abort("could not determine correct solution class - ${Constants.USAGE}")

    val elapsedTime = measureTimeMillis {
        puzzle?.process()
    }

    exit("Day $day Part $part1Or2 - Completed in $elapsedTime msec")
}