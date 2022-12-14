package mpdev.aoc2022.day15

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch

/*

 */
class InputProcessorDay15: InputProcessor<InputDay15>() {

    private fun processLine(line: String, datalist: MutableList<String>) {
        val pointsList = mutableListOf<String>()
        var s = line
        while (true) {
            when {
                s.matchRegExp(Regex("""^(\d+),(\d+) -> (.*)$""")) -> {
                    val (n1, n2, rest) = regexMatch!!.destructured
                    //pointsList.add(Pair(n1.toInt(), n2.toInt()))
                    s = rest
                }
                s.matchRegExp(Regex("""^(\d+),(\d+)$""")) -> {
                    val (n1, n2) = regexMatch!!.destructured
                    //pointsList.add(Pair(n1.toInt(), n2.toInt()))
                    break
                }
                else -> throw InputDataException("day 14 - bad line: $line")
            }
        }
        datalist.add("aaaa")
    }

    override fun process(input: List<String>): InputDay15 {
        val datalist = mutableListOf<String>()
        input.forEach { line -> processLine(line, datalist) }
        return InputDay15(datalist)
    }
}