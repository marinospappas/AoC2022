package mpdev.aoc2022.day21

import mpdev.aoc2022.common.*
import java.math.BigInteger

class SolutionProcessorDay21: SolutionProcessor<Day21> {

    /** part 1 calculation */
    override fun part1(puzzle: Day21): String {
        return puzzle.monkeyMap["root"]!!.getResult().toString()
    }

    /** part 2 calculation */
    override fun part2(puzzle: Day21): String {
        val left = puzzle.calcSideOfHuman()
        val branchResult: BigInteger
        if (left) {
            branchResult = puzzle.monkeyMap[puzzle.monkeyMap["root"]!!.s2]!!.getResult()
            println("human on the left branch - right branch result $branchResult")
        }
        else {
            branchResult = puzzle.monkeyMap[puzzle.monkeyMap["root"]!!.s1]!!.getResult()
            println("human on the right branch - left branch result $branchResult")
        }
        // adjust root's reverse calculation so that it will result in both sides of the root calc equal
        puzzle.monkeyMap["root"]!!.revCalc = when (puzzle.monkeyMap["root"]!!.op) {
            '+' -> { _, _ -> branchResult * BigInteger("2") }
            '-' -> { _, _ -> BigInteger("0") }
            '*' -> { _, _ -> branchResult * branchResult }
            '/' -> { _, _ -> BigInteger("1") }
            else -> { _, _ -> null }
        }
        // reverse calculate value for human
        return puzzle.monkeyMap["humn"]!!.getRevResult().toString()
    }

}