package mpdev.aoc2022.day11

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch

class InputProcessorDay11: InputProcessor<InputDay11>() {

    override fun process(input: List<String>): InputDay11 {
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
        return InputDay11(dataList)
    }
}