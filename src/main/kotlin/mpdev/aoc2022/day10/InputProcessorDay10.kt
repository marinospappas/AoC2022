package mpdev.aoc2022.day10

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.testMode
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch

class InputProcessorDay10: InputProcessor<InputDay10>() {

    override fun process(input: List<String>): InputDay10 {
        val dataList: MutableList<Pair<String,String>> = mutableListOf()
        input.forEach { line ->
            when {
                line.matchRegExp(Regex("""^(noop|addx) *(-?\d*)$""")) -> {
                    val (instr, operand) = regexMatch!!.destructured
                    dataList.add(Pair(instr, operand))
                }
                else -> throw InputDataException("bad input line: $line")
            }
        }
        return InputDay10(dataList)
    }
}