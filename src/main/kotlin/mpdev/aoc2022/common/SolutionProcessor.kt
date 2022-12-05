package mpdev.aoc2022.common

interface SolutionProcessor<I> {
    /** part 1 calculation */
    fun part1(input: I): String

    /** part 2 calculation */
    fun part2(input: I): String
}
