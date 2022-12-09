package mpdev.aoc2022.day09

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay09: InputProcessor<InputDay09>() {

    private var regexMatch: MatchResult? = null
    private fun String.matchRegExp(regex: Regex) = regex.find(this).also { regexMatch = it } != null

    override fun process(input: List<String>): InputDay09 {
        val dataList: MutableList<Pair<Char,Int>> = mutableListOf()
        input.forEach {line ->
            when {
                line.matchRegExp(Regex("""^([LRUD]) +(-?\d+)""")) -> {
                    val (dir, dist) = regexMatch!!.destructured
                    dataList.add(Pair(dir.first(), dist.toInt()))
                }
                else -> throw InputDataException("bad input line: $line")
            }
        }
        return InputDay09(Rope(mutableListOf()), dataList)
    }
}