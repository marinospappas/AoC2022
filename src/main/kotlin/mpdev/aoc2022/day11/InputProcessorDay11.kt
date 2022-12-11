package mpdev.aoc2022.day11

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch

class InputProcessorDay11: InputProcessor<InputDay11>() {
    /*
    Monkey 0:
      Starting items: 79, 98
      Operation: new = old * 19
      Test: divisible by 23
        If true: throw to monkey 2
        If false: throw to monkey 3
     */
    private fun decodeOperation(opStr: String): (Long) -> Long {
        when {
            opStr.matchRegExp(Regex("""^new = old \+ (\d+)$""")) -> {
                val (id) = regexMatch!!.destructured
                return { level -> level + id.toInt() }
            }
            opStr.matchRegExp(Regex("""^new = old \* (\d+)$""")) -> {
                val (id) = regexMatch!!.destructured
                return { level -> level * id.toInt() }
            }
            opStr.matchRegExp(Regex("""^new = old \+ old$""")) -> {
                return { level -> level + level }
            }
            opStr.matchRegExp(Regex("""^new = old \* old$""")) -> {
                return { level -> level * level }
            }
            else -> throw InputDataException("day 11: bad operation string: $opStr")
        }
    }

    override fun process(input: List<String>): InputDay11 {
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
                line.matchRegExp(Regex("""^ {2}Starting items: (.+)$""")) -> {
                    val (items) = regexMatch!!.destructured
                    itemsList = items.split(",").map { it.trim().toLong() } as MutableList<Long>
                }
                line.matchRegExp(Regex("""^ {2}Operation: (.+)$""")) -> {
                    val (opStr) = regexMatch!!.destructured
                    operationStr = opStr
                    operation = decodeOperation(opStr)
                }
                line.matchRegExp(Regex("""^ {2}Test: divisible by (\d+)$""")) -> {
                    val (divisby) = regexMatch!!.destructured
                    divisibleBy = divisby.toInt()
                }
                line.matchRegExp(Regex("""^ {4}If true: throw to monkey (\d)$""")) -> {
                    val (decT) = regexMatch!!.destructured
                    decisionT = decT.toInt()
                }
                line.matchRegExp(Regex("""^ {4}If false: throw to monkey (\d)$""")) -> {
                    val (decF) = regexMatch!!.destructured
                    decisionF = decF.toInt()
                }
                line.matchRegExp(Regex("""^$""")) -> {
                    dataList.add(Monkey(monkeyId, itemsList, operation, operationStr, divisibleBy, Pair(decisionT, decisionF)))
                }
                else -> throw InputDataException("day 11: bad input line: $line")
            }
        }
        dataList.add(Monkey(monkeyId, itemsList, operation, operationStr, divisibleBy, Pair(decisionT, decisionF)))
        return InputDay11(dataList)
    }
}