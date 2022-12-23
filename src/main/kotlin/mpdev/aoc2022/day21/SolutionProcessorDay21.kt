package mpdev.aoc2022.day21

import mpdev.aoc2022.common.*
import java.math.BigInteger

class SolutionProcessorDay21: SolutionProcessor<InputDay21> {

    /** part 1 calculation */
    override fun part1(input: InputDay21): String {
        return input.monkeyMap["root"]!!.getResult().toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay21): String {
        val left = input.calcSideOfHuman()
        val branchResult: BigInteger
        if (left) {
            branchResult = input.monkeyMap[input.monkeyMap["root"]!!.s2]!!.getResult()
            println("human on the left branch - right branch result $branchResult")
        }
        else {
            branchResult = input.monkeyMap[input.monkeyMap["root"]!!.s1]!!.getResult()
            println("human on the right branch - left branch result $branchResult")
        }
        // adjust root's reverse calculation so that it will result in both sides of the root calc equal
        input.monkeyMap["root"]!!.revCalc = when (input.monkeyMap["root"]!!.op) {
            '+' -> { _, _ -> branchResult * BigInteger("2") }
            '-' -> { _, _ -> BigInteger("0") }
            '*' -> { _, _ -> branchResult * branchResult }
            '/' -> { _, _ -> BigInteger("1") }
            else -> { _, _ -> null }
        }
        // reverse calculate value for human
        return input.monkeyMap["humn"]!!.getRevResult().toString()
    }

}