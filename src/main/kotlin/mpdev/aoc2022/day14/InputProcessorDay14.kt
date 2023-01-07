package mpdev.aoc2022.day14

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch

/*
498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9
 */
class InputProcessorDay14: InputProcessor<Day14>() {

    private fun processLine(line: String, datalist: MutableList<List<Pair<Int,Int>>>) {
        val pointsList = mutableListOf<Pair<Int,Int>>()
        var s = line
        while (true) {
            when {
                s.matchRegExp(Regex("""^(\d+),(\d+) -> (.*)$""")) -> {
                    val (n1, n2, rest) = regexMatch!!.destructured
                    pointsList.add(Pair(n1.toInt(), n2.toInt()))
                    s = rest
                }
                s.matchRegExp(Regex("""^(\d+),(\d+)$""")) -> {
                    val (n1, n2) = regexMatch!!.destructured
                    pointsList.add(Pair(n1.toInt(), n2.toInt()))
                    break
                }
                else -> throw InputDataException("day 14 - bad line: $line")
            }
        }
        datalist.add(pointsList)
    }

    override fun process(input: List<String>): Day14 {
        val datalist = mutableListOf<List<Pair<Int,Int>>>()
        input.forEach { line -> processLine(line, datalist) }
        return Day14(datalist)
    }
}