package mpdev.aoc2022.common

import mpdev.aoc2022.day01.ConstantsDay01
import mpdev.aoc2022.day01.InputLineDay01
import mpdev.aoc2022.day01.SolutionDay01
import mpdev.aoc2022.day02.ConstantsDay02
import mpdev.aoc2022.day02.InputLineDay02
import mpdev.aoc2022.day02.SolutionDay02
import mpdev.aoc2022.day03.ConstantsDay03
import mpdev.aoc2022.day03.InputLineDay03
import mpdev.aoc2022.day03.SolutionDay03
import mpdev.aoc2022.day04.ConstantsDay04
import mpdev.aoc2022.day04.InputLineDay04
import mpdev.aoc2022.day04.SolutionDay04

fun main(args: Array<String>) {

    val part1_2 = getPart1or2(args)
    val day = getDay(args)
    if (part1_2 == 0) abort(Constants.USAGE)
    if (day !in 1..25) abort(Constants.USAGE)
    val filename = "src/main/resources/day${String.format("%02d",day)}/input.txt"

    val puzzleSolution = when (day) {
        1 -> PuzzleSolution(part1_2, day, filename, InputLineDay01(), ConstantsDay01(), SolutionDay01())
        2 -> PuzzleSolution(part1_2, day, filename, InputLineDay02(), ConstantsDay02(), SolutionDay02())
        3 -> PuzzleSolution(part1_2, day, filename, InputLineDay03(), ConstantsDay03(), SolutionDay03())
        4 -> PuzzleSolution(part1_2, day, filename, InputLineDay04(), ConstantsDay04(), SolutionDay04())
        else -> null
    }
    if (puzzleSolution == null)
        abort("could not determine correct solution class - ${Constants.USAGE}")
    puzzleSolution?.process()
}