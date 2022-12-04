package mpdev.aoc2022.day04

import mpdev.aoc2022.common.InputLine
import mpdev.aoc2022.common.abort

class InputLineDay04: InputLine<GroupedSections> {
    override fun process(firstLine: Boolean, line: String, input: MutableList<GroupedSections>) {
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
}