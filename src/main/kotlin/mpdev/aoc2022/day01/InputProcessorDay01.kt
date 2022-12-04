package mpdev.aoc2022.day01

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay01: InputProcessor<Elf>() {

    companion object {
        var elfId = 0
    }

    override fun processLine(inputLine: String, dataList: MutableList<Elf>) {
        if (inputLine.isNotEmpty())
            dataList.add(Elf(elfId, inputLine.toInt()))
        else
            ++elfId
    }
}