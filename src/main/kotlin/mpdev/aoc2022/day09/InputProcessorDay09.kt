package mpdev.aoc2022.day09

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch

class InputProcessorDay09: InputProcessor<InputDay09>() {

    override fun process(input: List<String>): InputDay09 {
        val dataList: MutableList<Pair<Char,Int>> = mutableListOf()
        input.forEach {line ->
            when {
                line.matchRegExp(Regex("""^([LRUD]) +(-?\d+)""")) -> {
                    val (dir, dist) = regexMatch!!.destructured
                    dataList.add(Pair(dir.first(), dist.toInt()))
                }
                else -> throw InputDataException("day 9: bad input line: $line")
            }
        }
        return InputDay09(Rope(mutableListOf()), dataList)
    }
}