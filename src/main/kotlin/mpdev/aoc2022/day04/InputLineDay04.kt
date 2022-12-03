package mpdev.aoc2022.day04

import mpdev.aoc2022.common.InputLine
import mpdev.aoc2022.common.abort

class InputLineDay04: InputLine<Rucksack> {
    override fun process(firstLine: Boolean, line: String, input: MutableList<Rucksack>) {
        if (line.length % 2 != 0)
            abort("bad input line $line")
        val midLength = line.length / 2
        input.add(Rucksack(line.substring(0, midLength), line.substring(midLength)))
    }
}