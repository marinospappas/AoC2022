package mpdev.aoc2022.day23

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay23: InputProcessor<InputDay23>() {
    override fun process(input: List<String>): InputDay23 {
        return InputDay23(input)
    }
}