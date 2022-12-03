package mpdev.aoc2022.common

interface Constants {
    companion object {
        const val AOC = "AoC 2022"
        const val AUTHOR = "Marinos Pappas"
        const val USAGE = "usage: Main -part1|-part2 Input_File"
    }
    val DATE: String
    val PUZZLE: String
    val RESULT_STRING: String
    val RESULT_STRING2: String
}