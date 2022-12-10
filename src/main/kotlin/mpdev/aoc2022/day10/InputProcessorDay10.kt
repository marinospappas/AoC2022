package mpdev.aoc2022.day10

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.testMode
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch

class InputProcessorDay10: InputProcessor<InputDay10>() {

    override fun process(input: List<String>): InputDay10 {
        val dataList: MutableList<Pair<String,Int>> = mutableListOf()
        input.forEach {line ->
            when {
                line.matchRegExp(Regex("""^(noop)$""")) -> {
                    dataList.add(Pair(NOP, 0))
                }
                line.matchRegExp(Regex("""^(addx) (-?\d+)$""")) -> {
                    val (_, operand) = regexMatch!!.destructured
                    dataList.add(Pair(ADD, operand.toInt()))
                }
                else -> throw InputDataException("bad input line: $line")
            }
        }
        return InputDay10(dataList, 6)
    }
}