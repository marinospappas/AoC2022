package mpdev.aoc2022.day03

import mpdev.aoc2022.common.Input
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.abort

class InputProcessorDay03: InputProcessor<Rucksack> {
    private fun processLine(line: String, input: MutableList<Rucksack>) {
        if (line.length % 2 != 0)
            abort("bad input line $line")
        val midLength = line.length / 2
        input.add(Rucksack(line.substring(0, midLength), line.substring(midLength)))
    }

    override fun process(input: List<String>): Input<Rucksack> {
        val dataList = mutableListOf<Rucksack>()
        input.forEach { processLine(it, dataList) }
        return Input(dataList)
    }
}