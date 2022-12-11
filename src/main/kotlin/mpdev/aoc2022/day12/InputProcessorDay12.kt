package mpdev.aoc2022.day12

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch

class InputProcessorDay12: InputProcessor<InputDay12>() {
    /*
     */
    override fun process(input: List<String>): InputDay12 {
        val dataList: MutableList<Monkey> = mutableListOf()
        var monkeyId = 0
        var itemsList: MutableList<Long> = mutableListOf()
        var operation: (Long) -> Long = {0}
        var operationStr = ""
        var divisibleBy = 0
        var decisionT = 0
        var decisionF = 0
        input.forEach { line ->
            when {
                line.matchRegExp(Regex("""^Monkey (\d):$""")) -> {
                    val (id) = regexMatch!!.destructured
                    monkeyId = id.toInt()
                }
                else -> throw InputDataException("day 12: bad input line: $line")
            }
        }
        dataList.add(Monkey(monkeyId, itemsList, operation, operationStr, divisibleBy, Pair(decisionT, decisionF)))
        return InputDay12(dataList)
    }
}