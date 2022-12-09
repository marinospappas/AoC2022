package mpdev.aoc2022.day10

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay10: InputProcessor<InputDay10>() {

    private var regexMatch: MatchResult? = null
    private fun String.matchRegExp(regex: Regex) = regex.find(this).also { regexMatch = it } != null

    override fun process(input: List<String>): InputDay10 {
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
        return InputDay10(mutableListOf())
    }
}