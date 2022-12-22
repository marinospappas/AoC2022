package mpdev.aoc2022.day21

import mpdev.aoc2022.common.*

class SolutionProcessorDay21: SolutionProcessor<InputDay21> {

    /** part 1 calculation */
    override fun part1(input: InputDay21): String {
        return input.monkeyMap["root"]!!.getResult().toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay21): String {
        val left = true // calculate side of human
        if (left) {
            val branchResult = input.monkeyMap[input.monkeyMap["root"]!!.s2]!!.getResult()
            input.monkeyMap[input.monkeyMap["root"]!!.s1] = InputDay21.Monkey(id=input.monkeyMap["root"]!!.s1, number=branchResult)
            println("right branch result $branchResult")
        }
        else {
            val branchResult = input.monkeyMap[input.monkeyMap["root"]!!.s1]!!.getResult()
            input.monkeyMap[input.monkeyMap["root"]!!.s2] = InputDay21.Monkey(id=input.monkeyMap["root"]!!.s2, number=branchResult)
        }
        input.monkeyMap.forEach { (k, v) -> println("$k: $v") }
        return input.monkeyMap["humn"]!!.getRevResult().toString()
    }

}