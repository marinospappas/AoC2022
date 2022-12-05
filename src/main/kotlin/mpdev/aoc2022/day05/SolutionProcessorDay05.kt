package mpdev.aoc2022.day05

import mpdev.aoc2022.common.*

class SolutionProcessorDay05: SolutionProcessor<InputDay05> {

    private fun executeMove(count: Int, src: String, dest: String, reverse: Boolean = false): List<String> {
        val cnt = if (count > src.length) src.length else count
        val strToMove = src.substring(src.length - cnt)
        val resDest = dest + if (reverse) strToMove.reversed() else strToMove
        val resSrc = src.substring(0, src.length - cnt)
        return listOf(resSrc, resDest)
    }

    /** part 1 calculation */
    override fun part1(input: InputDay05): String {
        input.moves.forEach {
            val movResult = executeMove(it.count, input.stacks[it.src-1], input.stacks[it.dest-1], true)
            input.stacks[it.src-1] = movResult[0]
            input.stacks[it.dest-1] = movResult[1]
        }
        var result = ""
        input.stacks.forEach { result += it.last() }
        return result
    }


    /** part 2 calculation */
    override fun part2(input: InputDay05): String {
        input.moves.forEach {
            val movResult = executeMove(it.count, input.stacks[it.src-1], input.stacks[it.dest-1])
            input.stacks[it.src-1] = movResult[0]
            input.stacks[it.dest-1] = movResult[1]
        }
        var result = ""
        input.stacks.forEach { result += it.last() }
        return result
    }
}