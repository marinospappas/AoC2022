package mpdev.aoc2022.day17

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay17: InputProcessor<InputDay17>() {

    override fun process(input: List<String>): InputDay17 {
        return InputDay17(input.joinToString("").toList())
    }
}