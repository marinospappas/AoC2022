package mpdev.aoc2022.day05

import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.abort

class InputProcessorDay05: InputProcessor<GroupedSections>() {
    override fun processLine(inputLine: String, dataList: MutableList<GroupedSections>) {
        val sections = inputLine.split(",")
        if (sections.size != 2)
            abort("bad input line $inputLine")
        dataList.add(GroupedSections(emptyList()))
    }

}