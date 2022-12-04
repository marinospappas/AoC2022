package mpdev.aoc2022.day01

import mpdev.aoc2022.common.*

class SolutionProcessorDay01: SolutionProcessor<Elf, Input<Elf>> {

    /** part 1 calculation */
    override fun part1(input: Input<Elf>): Result1 {
        val caloriesList = input.inputList.groupingBy { it.id }.aggregate {
                _, accumulator: Int?, element, first ->
            if (first) element.calories else accumulator?.plus(element.calories)
        }.values.toMutableList().sortedBy { it }
        return Result1(caloriesList.last()!!)
    }

    /** part 2 calculation */
    override fun part2(input: Input<Elf>): Result2 {
        val caloriesList = input.inputList.groupingBy { it.id }.aggregate {
                _, accumulator: Int?, element, first ->
            if (first) element.calories else accumulator?.plus(element.calories)
        }.values.toMutableList().sortedBy { it }
        val lastIndex = caloriesList.lastIndex
        return Result2(caloriesList[lastIndex]?.plus(caloriesList[lastIndex-1]!!)?.plus(caloriesList[lastIndex-2]!!) ?: 0)
    }
}