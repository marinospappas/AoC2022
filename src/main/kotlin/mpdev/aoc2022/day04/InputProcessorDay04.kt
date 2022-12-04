package mpdev.aoc2022.day04

import mpdev.aoc2022.common.Input
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.abort

class InputProcessorDay04: InputProcessor<GroupedSections> {
    private fun processLine(line: String, input: MutableList<GroupedSections>) {
        val sections = line.split(",")
        if (sections.size != 2)
            abort("bad input line $line")
        val sectionList = mutableListOf<Section>()
        sections.forEach {
            val startEnd = it.split("-")
            if (startEnd.size != 2)
                abort("bad input line $line")
            sectionList.add(Section(startEnd[0].toInt(), startEnd[1].toInt()))
        }
        input.add(GroupedSections(sectionList))
    }

    override fun process(input: List<String>): Input<GroupedSections> {
        val dataList = mutableListOf<GroupedSections>()
        input.forEach { processLine(it, dataList) }
        return Input(dataList)
    }
}