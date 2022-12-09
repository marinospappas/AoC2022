package mpdev.aoc2022.day03

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay03: InputProcessor<InputDay03>() {
    fun processLine(inputLine: String, dataList: MutableList<Rucksack>) {
        if (inputLine.length % 2 != 0)
            throw InputDataException("bad input line $inputLine")
        val midLength = inputLine.length / 2
        dataList.add(Rucksack(inputLine.substring(0, midLength), inputLine.substring(midLength)))
    }

    override fun process(input: List<String>): InputDay03 {
        val dataList = mutableListOf<Rucksack>()
        input.forEach { processLine(it, dataList) }
        return InputDay03(dataList)
    }
}