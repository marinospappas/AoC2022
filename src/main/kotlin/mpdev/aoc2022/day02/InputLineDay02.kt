package mpdev.aoc2022.day02

import mpdev.aoc2022.common.InputLine
import mpdev.aoc2022.common.abort

class InputLineDay02: InputLine<PlayData> {

    override fun process(firstLine: Boolean, line: String, input: MutableList<PlayData>) {
        val inputData = line.split(" ")
        if (inputData.size != 2)
            abort("bad input line $line")
        input.add(PlayData(inputData[0].first(), inputData[1].first()))
    }
}