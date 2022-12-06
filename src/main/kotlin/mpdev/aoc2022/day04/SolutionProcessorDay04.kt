package mpdev.aoc2022.day04

import mpdev.aoc2022.common.*

/* class Pair extensions */
fun Pair<Int,Int>.contains(other: Pair<Int,Int>): Boolean {
    return other.first >= this.first && other.first <= this.second
            && other.second >= this.first && other.second <= this.second
}

fun Pair<Int,Int>.overlaps(other: Pair<Int,Int>): Boolean {
    return other.first >= this.first && other.first <= this.second
            || other.second >= this.first && other.second <= this.second
}

class SolutionProcessorDay04: SolutionProcessor<InputDay04> {

    /** part 1 calculation */
    override fun part1(input: InputDay04): String {
        return input.inputList.count {
            it.sectionList[0].contains(it.sectionList[1]) || it.sectionList[1].contains(it.sectionList[0])
        }.toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay04): String {
        return input.inputList.count {
            it.sectionList[0].overlaps(it.sectionList[1]) || it.sectionList[1].overlaps(it.sectionList[0])
        }.toString()
    }
}
