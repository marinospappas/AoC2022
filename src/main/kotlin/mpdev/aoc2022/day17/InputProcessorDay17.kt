package mpdev.aoc2022.day17

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay17: InputProcessor<Day17>() {

    override fun process(input: List<String>): Day17 {
        return Day17(input.joinToString("").toList())
    }
}