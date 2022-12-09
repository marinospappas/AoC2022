package mpdev.aoc2022.day02

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay02: InputProcessor<InputDay02>() {
    fun processLine(inputLine: String, dataList: MutableList<PlayData>) {
        val inputData = inputLine.split(" ")
        if (inputData.size != 2)
            throw InputDataException("bad input line $inputLine")
        dataList.add(PlayData(inputData[0].first(), inputData[1].first()))
    }

    override fun process(input: List<String>): InputDay02 {
        val dataList = mutableListOf<PlayData>()
        input.forEach { processLine(it, dataList) }
        return InputDay02(dataList)
    }
}