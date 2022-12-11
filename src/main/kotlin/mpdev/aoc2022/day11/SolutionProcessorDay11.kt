package mpdev.aoc2022.day11

import mpdev.aoc2022.common.*

class SolutionProcessorDay11: SolutionProcessor<InputDay11> {

    /** part 1 calculation */
    override fun part1(input: InputDay11): String {
        for (round in 1..20) {
            input.monkeyList.forEach { monkey ->
                input.processMonkeyOutcome(monkey.play { x -> x / 3 })
                if (testMode) println("round $round after monkey ${monkey.id} has played\n${input.monkeyList.convertToString()}")
            }
        }
        val inspectedList = input.monkeyList.map { it.numberInspected }.toList().sorted().reversed()
        return (inspectedList[0].toLong() * inspectedList[1].toLong()).toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay11): String {
        val divisors = input.monkeyList.map { it.divisibleBy }.toList()
        val moduloBy = divisors.reduce { acc, item -> acc * item }
        for (round in 1..10000) {
            input.monkeyList.forEach { monkey -> input.processMonkeyOutcome(monkey.play { x-> x % moduloBy }) }
            if (testMode && setOf(1,2,3,4,20,1000,2000,3000,4000,5000,6000,7000,8000,9000,10000).contains(round))
                if (testMode) println("after round $round\n${input.monkeyList.convertToString()}")
        }
        val inspectedList = input.monkeyList.map { it.numberInspected }.toList().sorted().reversed()
        return (inspectedList[0].toLong() * inspectedList[1].toLong()).toString()
    }
}