package mpdev.aoc2022.common

interface Solution<T> {
    /** part 1 calculation */
    fun part1(input: Input<T>): Result1

    /** part 2 calculation */
    fun part2(input: Input<T>): Result2
}
