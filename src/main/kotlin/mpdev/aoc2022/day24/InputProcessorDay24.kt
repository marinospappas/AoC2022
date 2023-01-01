package mpdev.aoc2022.day24

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay24: InputProcessor<InputDay24>() {
    override fun process(input: List<String>) = InputDay24(input)

}