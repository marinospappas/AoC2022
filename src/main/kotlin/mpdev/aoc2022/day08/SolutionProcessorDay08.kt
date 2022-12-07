package mpdev.aoc2022.day08

import mpdev.aoc2022.common.*

class SolutionProcessorDay08: SolutionProcessor<InputDay08> {

    /** part 1 calculation */
    override fun part1(input: InputDay08): String {
        return input.root.getDirSizes().values.sorted().stream().filter { it <= 100000 }.toList().sum().toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay08): String {
        val totalSpace = 70000000
        val reqFreeSpace = 30000000
        val requiredToFree = reqFreeSpace - (totalSpace - input.root.getDirSize())
        val (dirName, dirSize) =
            input.root.getDirSizes().filterValues { it > requiredToFree }.minByOrNull { (_, value) -> value }!!
        println ("$dirName: $dirSize")
        return dirSize.toString()
    }
}