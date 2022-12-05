package mpdev.aoc2022.day05

import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.abort

class InputProcessorDay05: InputProcessor<InputDay05>() {

    fun processLine1(inputLine: String, dataList: MutableList<String>, numStacks: Int) {
        for (i in 1..numStacks) {
            val index = (i - 1) * 4 + 1
            if (index < inputLine.length)
                if (inputLine[index] in 'A'..'Z')
                    dataList[i - 1] = dataList[i-1] + (inputLine[index])
        }
    }

    fun processLine2(inputLine: String, dataList: MutableList<Move>) {
        val split = inputLine.split(" ")
        if (split.size != 6)
            abort("bad input line $inputLine")
        dataList.add(Move(split[1].toInt(), split[3].toInt(), split[5].toInt()))
    }

    override fun process(input: List<String>): InputDay05 {
        val stacks = mutableListOf<String>()
        val moves = mutableListOf<Move>()

        val numStacksLine = input.stream().filter { it.matches(Regex("^[0-9\\s]*\$")) }.toList()[0]
        val numStacks = numStacksLine.split(" ").last().toInt()
        for (i in 1..numStacks)
            stacks.add("")
        var inputPart1 = true
        input.forEach {
            if (inputPart1) {
                processLine1(it, stacks, numStacks)
                if (it.isEmpty())
                    inputPart1 = false
            } else
                processLine2(it, moves)
        }
        for (i in 0 until stacks.size) {
            stacks[i] = stacks[i].reversed()
        }
        return InputDay05(stacks, moves)
    }

}