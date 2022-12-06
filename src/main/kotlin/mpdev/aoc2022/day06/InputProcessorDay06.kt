package mpdev.aoc2022.day06

import mpdev.aoc2022.common.InputProcessor
import java.lang.StringBuilder

class InputProcessorDay06: InputProcessor<InputDay06>() {

    override fun process(input: List<String>): InputDay06 {
        return InputDay06(StringBuilder().also { input.forEach { line -> it.append(line) } }.toString())
    }

}