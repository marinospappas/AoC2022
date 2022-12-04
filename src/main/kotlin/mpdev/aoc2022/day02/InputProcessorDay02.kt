package mpdev.aoc2022.day02

import mpdev.aoc2022.common.Input
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.abort

class InputProcessorDay02: InputProcessor<PlayData> {
    private fun processLine(line: String, input: MutableList<PlayData>) {
        val inputData = line.split(" ")
        if (inputData.size != 2)
            abort("bad input line $line")
        input.add(PlayData(inputData[0].first(), inputData[1].first()))
    }

    override fun process(input: List<String>): Input<PlayData> {
        val dataList = mutableListOf<PlayData>()
        input.forEach { processLine(it, dataList) }
        return Input(dataList)
    }
}