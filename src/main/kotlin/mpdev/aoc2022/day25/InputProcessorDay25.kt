package mpdev.aoc2022.day25

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay25: InputProcessor<Day25>() {
    override fun process(input: List<String>): Day25 {
        return Day25(input)
    }
}