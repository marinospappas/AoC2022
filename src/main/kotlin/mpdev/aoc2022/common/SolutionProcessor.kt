package mpdev.aoc2022.common

interface SolutionProcessor<T,U: Input<T>> {
    /** part 1 calculation */
    fun part1(input: U): Result1

    /** part 2 calculation */
    fun part2(input: U): Result2
}
