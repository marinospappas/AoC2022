package mpdev.aoc2022.day05

import mpdev.aoc2022.common.*

class SolutionProcessorDay05: SolutionProcessor<InputDay05> {

    fun executeMove2(count: Int, src: String, dest: String): List<String> {
        val cnt = if (count > src.length) src.length else count
        val strToMove = src.substring(src.length - cnt)
        val resDest = dest + strToMove
        val resSrc = src.substring(0, src.length - cnt)
        return listOf(resSrc, resDest)
    }

    fun executeMove1(count: Int, src: String, dest: String): List<String> {
        val cnt = if (count > src.length) src.length else count
        var resDest = dest
        var resSrc = src
        for (i in 0 until cnt) {
            val charToMove = resSrc.last()
            resSrc = resSrc.substring(0, resSrc.length-1)
            resDest = resDest + charToMove
        }
        return listOf(resSrc, resDest)
    }

    /** part 1 calculation */
    override fun part1(input: InputDay05): String {
        input.moves.forEach {
            val movResult = executeMove1(it.count, input.stacks[it.src-1], input.stacks[it.dest-1])
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
            val movResult = executeMove2(it.count, input.stacks[it.src-1], input.stacks[it.dest-1])
            input.stacks[it.src-1] = movResult[0]
            input.stacks[it.dest-1] = movResult[1]
        }
        var result = ""
        input.stacks.forEach { result += it.last() }
        return result
    }
}