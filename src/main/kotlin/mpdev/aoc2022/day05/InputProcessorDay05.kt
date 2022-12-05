package mpdev.aoc2022.day05

import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.abort
import java.lang.Exception

class InputProcessorDay05: InputProcessor<InputDay05>() {

    private fun processStack(inputLine: String, dataList: MutableList<String>, numStacks: Int) {
        for (i in 1..numStacks) {
            val index = (i - 1) * 4 + 1
            if (index < inputLine.length)
                if (inputLine[index] in 'A'..'Z')
                    dataList[i - 1] = dataList[i-1] + (inputLine[index])
        }
    }

    private fun processMove(inputLine: String, dataList: MutableList<Move>) {
        val matches = """move (\d+) from (\d+) to (\d+)""".toRegex().find(inputLine)
        try {
            val (cnt, from, to) = matches!!.destructured
            dataList.add(Move(cnt.toInt(), from.toInt(), to.toInt()))
        }
        catch (e: Exception) { abort("bad input line $inputLine") }
    }

    override fun process(input: List<String>): InputDay05 {
        val stacks = mutableListOf<String>()
        val moves = mutableListOf<Move>()

        val numStacks = input.stream().filter { it.matches(Regex("^[0-9+\\s*]+\$")) }
            .toList()[0].split(" ").last().toInt()
        for (i in 1..numStacks)
            stacks.add("")
        var inputPart1 = true
        input.forEach {
            if (inputPart1) {
                processStack(it, stacks, numStacks)
                if (it.isEmpty())
                    inputPart1 = false
            } else
                processMove(it, moves)
        }
        for (i in 0 until stacks.size) {
            stacks[i] = stacks[i].reversed()
        }
        return InputDay05(stacks, moves)
    }

}