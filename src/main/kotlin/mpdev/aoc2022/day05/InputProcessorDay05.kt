package mpdev.aoc2022.day05

import mpdev.aoc2022.common.Input
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.abort

class InputProcessorDay05: InputProcessor<GroupedSections> {
    private fun processLine(line: String, input: MutableList<GroupedSections>) {
        val sections = line.split(",")
        if (sections.size != 2)
            abort("bad input line $line")
        val sectionList = mutableListOf<String>()

        input.add(GroupedSections(sectionList))
    }

    override fun process(input: List<String>): Input<GroupedSections> {
        val dataList = mutableListOf<GroupedSections>()
        input.forEach { processLine(it, dataList) }
        return Input(dataList)
    }
}