package mpdev.aoc2022.common

import mpdev.aoc2022.day03.ConstantsDay03
import mpdev.aoc2022.day03.InputLineDay03
import mpdev.aoc2022.day03.SolutionDay03

fun main(args: Array<String>) {

    val part1_2 = getPart1or2(args)
    val day = getDay(args)
    if (part1_2 == 0) abort(Constants.USAGE)
    if (day !in 1..25) abort(Constants.USAGE)
    val filename = "src/main/resources/day${String.format("%02d",day)}/input.txt"

    val puzzleSolution = PuzzleSolution(part1_2, day, filename, InputLineDay03(), ConstantsDay03(), SolutionDay03())

    puzzleSolution.process()
}