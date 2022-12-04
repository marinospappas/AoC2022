package mpdev.aoc2022.day01

import mpdev.aoc2022.common.Input
import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay01: InputProcessor<Elf> {

    companion object {
        var elfId = 0
    }

    private fun processLine(line: String, input: MutableList<Elf>) {
        if (line.isNotEmpty())
            input.add(Elf(elfId, line.toInt()))
        else
            ++elfId
    }

    override fun process(input: List<String>): Input<Elf> {
        val dataList = mutableListOf<Elf>()
        input.forEach { processLine(it, dataList) }
        return Input(dataList)
    }
}