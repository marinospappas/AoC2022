package mpdev.aoc2022.day02

import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.abort

class InputProcessorDay02: InputProcessor<PlayData>() {
    override fun processLine(inputLine: String, dataList: MutableList<PlayData>) {
        val inputData = inputLine.split(" ")
        if (inputData.size != 2)
            abort("bad input line $inputLine")
        dataList.add(PlayData(inputData[0].first(), inputData[1].first()))
    }
}