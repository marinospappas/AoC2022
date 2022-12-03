package mpdev.aoc2022.day01

import mpdev.aoc2022.common.InputLine

class InputLineDay01: InputLine<Elf> {
    companion object {
        var elfId = 0
    }

    override fun process(firstLine: Boolean, line: String, input: MutableList<Elf>) {
        if (line.isNotEmpty())
            input.add(Elf(elfId, line.toInt()))
        else
            ++elfId
    }
}