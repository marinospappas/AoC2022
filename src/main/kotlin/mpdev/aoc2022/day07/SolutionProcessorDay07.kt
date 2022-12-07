package mpdev.aoc2022.day07

import mpdev.aoc2022.common.*

class SolutionProcessorDay07: SolutionProcessor<InputDay07> {

    /** part 1 calculation */
    override fun part1(input: InputDay07): String {
        input.root.updateDirSizes()
        return input.root.getDirSizes().values.sorted().stream().filter { it <= 100000 }.toList().sum().toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay07): String {
        val totalSpace = 70000000
        val reqFreeSpace = 30000000
        input.root.updateDirSizes()
        val requiredToFree = reqFreeSpace - (totalSpace - input.root.getDirSize())
        val (dirName, dirSize) =
            input.root.getDirSizes().filterValues { it > requiredToFree }.toList().minByOrNull { (_, value) -> value }!!
        println ("$dirName: $dirSize")
        return dirSize.toString()
    }
}