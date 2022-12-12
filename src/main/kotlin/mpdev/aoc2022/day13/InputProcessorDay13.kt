package mpdev.aoc2022.day13

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay13: InputProcessor<InputDay13>() {

    override fun process(input: List<String>): InputDay13 {
        return InputDay13(input)
    }
}