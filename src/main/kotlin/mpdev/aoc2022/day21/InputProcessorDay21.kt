package mpdev.aoc2022.day21

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.day21.InputDay21.Monkey
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch
import java.math.BigInteger

class InputProcessorDay21: InputProcessor<InputDay21>() {

    /*
    dbpl: 5
    cczh: sllz + lgvd
     */
    private fun processLine(line: String, dataMap: MutableMap<String, Monkey>, parentMap: MutableMap<String,Pair<String,Boolean>>) {
        when {
            line.matchRegExp(Regex("""^([a-z]+): (-?\d+)""")) -> {
                val (s1, n) = regexMatch!!.destructured
                dataMap[s1] = Monkey(id=s1, number = BigInteger(n))
            }
            line.matchRegExp(Regex("""^([a-z]+): ([a-z+]+) ([+\-*/]) ([a-z]+)""")) -> {
                val (s1, s2, op, s3) = regexMatch!!.destructured
                dataMap[s1] = Monkey(s1, s2, s3, op.first())
                // also update mapping of parent, "true" means that the monkey is on the left hand side of the math operation
                parentMap[s2] = Pair(s1, true)
                parentMap[s3] = Pair(s1, false)
            }
            else -> throw InputDataException("day 21 - bad line: $line")
        }
    }

    override fun process(input: List<String>): InputDay21 {
        val dataMap = mutableMapOf<String,Monkey>()
        val parent = mutableMapOf<String,Pair<String,Boolean>>()
        input.forEach { processLine(it, dataMap, parent) }
        return InputDay21(dataMap, parent)
    }
}