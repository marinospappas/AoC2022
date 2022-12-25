package mpdev.aoc2022.day25

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay25: InputProcessor<InputDay25>() {
    override fun process(input: List<String>): InputDay25 {
        return InputDay25(input)
    }
}