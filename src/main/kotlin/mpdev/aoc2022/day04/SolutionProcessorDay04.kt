package mpdev.aoc2022.day04

import mpdev.aoc2022.common.*

class SolutionProcessorDay04: SolutionProcessor<GroupedSections, Input<GroupedSections>> {

    /** part 1 calculation */
    override fun part1(input: Input<GroupedSections>): Result1 {
        return Result1(input.inputList.count {
            it.sectionList[0].contains(it.sectionList[1]) || it.sectionList[1].contains(it.sectionList[0])
        })
    }


    /** part 2 calculation */
    override fun part2(input: Input<GroupedSections>): Result2 {
        return Result2(input.inputList.count {
            it.sectionList[0].overlaps(it.sectionList[1]) || it.sectionList[1].overlaps(it.sectionList[0])
        })
    }
}