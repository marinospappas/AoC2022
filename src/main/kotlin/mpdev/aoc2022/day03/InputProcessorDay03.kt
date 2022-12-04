package mpdev.aoc2022.day03

import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.abort

class InputProcessorDay03: InputProcessor<Rucksack>() {
    override fun processLine(inputLine: String, dataList: MutableList<Rucksack>) {
        if (inputLine.length % 2 != 0)
            abort("bad input line $inputLine")
        val midLength = inputLine.length / 2
        dataList.add(Rucksack(inputLine.substring(0, midLength), inputLine.substring(midLength)))
    }

}