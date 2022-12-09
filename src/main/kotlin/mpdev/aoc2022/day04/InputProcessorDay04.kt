package mpdev.aoc2022.day04

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay04: InputProcessor<InputDay04>() {
    private fun processLine(inputLine: String, dataList: MutableList<GroupedSections>) {
        val sections = inputLine.split(",")
        if (sections.size != 2)
            throw InputDataException("bad input line $inputLine")
        val sectionList = mutableListOf<Pair<Int,Int>>()
        sections.forEach {
            val startEnd = it.split("-")
            if (startEnd.size != 2)
                throw InputDataException("bad input line $inputLine")
            sectionList.add(Pair(startEnd[0].toInt(), startEnd[1].toInt()))
        }
        dataList.add(GroupedSections(sectionList))
    }

    override fun process(input: List<String>): InputDay04 {
        return InputDay04(mutableListOf<GroupedSections>().also { input.forEach { line -> processLine(line, it) } })
    }
}