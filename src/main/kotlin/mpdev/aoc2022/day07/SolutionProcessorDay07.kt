package mpdev.aoc2022.day07

import mpdev.aoc2022.common.*
import java.lang.StringBuilder
import java.util.Comparator

class SolutionProcessorDay07: SolutionProcessor<InputDay07> {

    /** part 1 calculation */
    override fun part1(input: InputDay07): String {
        input.root.updateDirSizes()
        val dirSizes = input.root.getDirSizes()
        val res = dirSizes.values.sorted().stream().filter { it <= 100000 }.toList().sum()
        return res.toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay07): String {
        val totalSpace = 70000000
        val reqFreeSpace = 30000000
        input.root.updateDirSizes()
        val dirSizes = input.root.getDirSizes()
        val totalSize = input.root.getDirSize()
        val curFreeSpace = totalSpace - totalSize
        val requiredToFree = reqFreeSpace - curFreeSpace
        return println( dirSizes.filterValues { it > requiredToFree }.toList().sortedBy {  (_, value) -> value }
            .minOf { (_, value) -> value  } ).toString()
    }
}