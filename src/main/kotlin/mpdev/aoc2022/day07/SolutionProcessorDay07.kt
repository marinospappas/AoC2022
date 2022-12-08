package mpdev.aoc2022.day07

import mpdev.aoc2022.common.*

class SolutionProcessorDay07: SolutionProcessor<InputDay07> {

    /** part 1 calculation */
    override fun part1(input: InputDay07): String {
        return input.root.getDirSizes().sorted().stream().filter { it <= 100000 }.toList().sum().toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay07): String {
        val totalSpace = 70000000
        val reqFreeSpace = 30000000
        val requiredToFree = reqFreeSpace - (totalSpace - input.root.getDirSize(input.root))
        return    input.root.getDirSizes().stream().filter { it > requiredToFree }.toList().min().toString()
    }
}