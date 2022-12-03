package mpdev.aoc2022.day03

import mpdev.aoc2022.common.Input
import mpdev.aoc2022.common.Result1
import mpdev.aoc2022.common.Result2
import mpdev.aoc2022.common.Solution
import java.util.*

class SolutionDay03: Solution<Rucksack> {

    /** priorities */
    fun getPriority(item: Char): Int {
        return when (item) {
            in 'a'..'z' -> item.code - 'a'.code + 1
            in 'A'..'Z' -> item.code - 'A'.code + 27
            else -> 0
        }
    }

    /**
     * common items across the two compartments
     * or in other words: common characters across two strings
     */
    fun getCommonItems(s1: String, s2: String): List<Char> {
        val commonItems = mutableListOf<Char>()
        s1.forEach { c -> if (s2.contains(c)) commonItems.add(c) }
        return commonItems.distinct()
    }

    /** part 1 calculation */
    override fun part1(input: Input<Rucksack>): Result1 {
        return Result1(input.inputList.sumOf {
            getCommonItems(it.comp1, it.comp2).sumOf { c -> getPriority(c) }
        })
    }

    /**
     * the common item across a list of rucksacks
     * or in other words: the common character across a number of strings
     */
    fun getCommonInGroup(group: List<Rucksack>): Char {
        val alphabet = "abcdefghijklmnopqrstuvwxyz"
        var commonItems: List<Char> = (alphabet+ alphabet.uppercase(Locale.getDefault())).toList()
        group.forEach { commonItems = getCommonItems(it.comp1 + it.comp2, commonItems.toString()) }
        return commonItems[0]
    }

    fun getGroups(input: Input<Rucksack>): Map<Int, List<Rucksack>> {
        return input.inputList.groupBy { input.inputList.indexOf(it) / 3 }
    }

    /** part 2 calculation */
    override fun part2(input: Input<Rucksack>): Result2 {
        return Result2(getGroups(input).values.sumOf { getPriority(getCommonInGroup(it)) })
    }
}