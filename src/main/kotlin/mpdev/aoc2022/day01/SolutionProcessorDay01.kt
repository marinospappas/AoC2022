package mpdev.aoc2022.day01

import mpdev.aoc2022.common.*

class SolutionProcessorDay01: SolutionProcessor<InputDay01> {

    /** part 1 calculation */
    override fun part1(input: InputDay01): String {
        val caloriesList = input.inputList.groupingBy { it.id }.aggregate {
                _, accumulator: Int?, element, first ->
            if (first) element.calories else accumulator?.plus(element.calories)
        }.values.toMutableList().sortedBy { it }
        return caloriesList.last()!!.toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay01): String {
        val caloriesList = input.inputList.groupingBy { it.id }.aggregate {
                _, accumulator: Int?, element, first ->
            if (first) element.calories else accumulator?.plus(element.calories)
        }.values.toMutableList().sortedBy { it }
        val lastIndex = caloriesList.lastIndex
        return (caloriesList[lastIndex]?.plus(caloriesList[lastIndex-1]!!)?.plus(caloriesList[lastIndex-2]!!) ?: 0).toString()
    }
}