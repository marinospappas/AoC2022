package mpdev.aoc2022.day01

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay01: InputProcessor<InputDay01>() {

    companion object {
        var elfId = 0
    }

    private fun processLine(inputLine: String, dataList: MutableList<Elf>) {
        if (inputLine.isNotEmpty())
            dataList.add(Elf(elfId, inputLine.toInt()))
        else
            ++elfId
    }

    override fun process(input: List<String>): InputDay01 {
        val dataList = mutableListOf<Elf>()
        input.forEach { processLine(it, dataList) }
        return InputDay01(dataList)
    }
}