package mpdev.aoc2022.day04

import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.abort

class InputProcessorDay04: InputProcessor<InputDay04>() {
    fun processLine(inputLine: String, dataList: MutableList<GroupedSections>) {
        val sections = inputLine.split(",")
        if (sections.size != 2)
            abort("bad input line $inputLine")
        val sectionList = mutableListOf<Section>()
        sections.forEach {
            val startEnd = it.split("-")
            if (startEnd.size != 2)
                abort("bad input line $inputLine")
            sectionList.add(Section(startEnd[0].toInt(), startEnd[1].toInt()))
        }
        dataList.add(GroupedSections(sectionList))
    }

    override fun process(input: List<String>): InputDay04 {
        val dataList = mutableListOf<GroupedSections>()
        input.forEach { processLine(it, dataList) }
        return InputDay04(dataList)
    }
}