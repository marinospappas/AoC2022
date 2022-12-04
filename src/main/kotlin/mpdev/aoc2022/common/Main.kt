package mpdev.aoc2022.common

import mpdev.aoc2022.day01.ConstantsDay01
import mpdev.aoc2022.day01.InputProcessorDay01
import mpdev.aoc2022.day01.SolutionProcessorDay01
import mpdev.aoc2022.day02.ConstantsDay02
import mpdev.aoc2022.day02.InputProcessorDay02
import mpdev.aoc2022.day02.SolutionProcessorDay02
import mpdev.aoc2022.day03.ConstantsDay03
import mpdev.aoc2022.day03.InputProcessorDay03
import mpdev.aoc2022.day03.SolutionProcessorDay03
import mpdev.aoc2022.day04.ConstantsDay04
import mpdev.aoc2022.day04.InputProcessorDay04
import mpdev.aoc2022.day04.SolutionProcessorDay04
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    val part1Or2 = getPart1or2(args)
    val day = getDay(args)
    if (part1Or2 == 0)
        abort(Constants.USAGE)
    if (day !in 1..25)
        abort(Constants.USAGE)
    val filename = "src/main/resources/day${String.format("%02d", day)}/input.txt"

    val puzzle = when (day) {
        1 -> PuzzleProcessor(part1Or2, day, ConstantsDay01(), getInput(filename), InputProcessorDay01(), SolutionProcessorDay01())
        2 -> PuzzleProcessor(part1Or2, day, ConstantsDay02(), getInput(filename), InputProcessorDay02(), SolutionProcessorDay02())
        3 -> PuzzleProcessor(part1Or2, day, ConstantsDay03(), getInput(filename), InputProcessorDay03(), SolutionProcessorDay03())
        4 -> PuzzleProcessor(part1Or2, day, ConstantsDay04(), getInput(filename), InputProcessorDay04(), SolutionProcessorDay04())
        else -> null
    }
    if (puzzle == null)
        abort("could not determine correct solution class - ${Constants.USAGE}")
    val elapsedTime = measureTimeMillis { puzzle?.process() }
    exit("Day $day Part $part1Or2 - Completed in $elapsedTime msec")
}