package mpdev.aoc2022.day23

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay23: InputProcessor<Day23>() {
    override fun process(input: List<String>): Day23 {
        return Day23(input)
    }
}