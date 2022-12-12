package mpdev.aoc2022.day12

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay12: InputProcessor<InputDay12>() {
    override fun process(input: List<String>): InputDay12 {
        return InputDay12(input)
    }
}