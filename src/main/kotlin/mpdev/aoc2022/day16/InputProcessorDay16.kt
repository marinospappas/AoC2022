package mpdev.aoc2022.day16

import mpdev.aoc2022.common.InputDataException
import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.utils.matchRegExp
import mpdev.aoc2022.utils.regexMatch
import java.awt.Point

/*
Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
 */
class InputProcessorDay16: InputProcessor<InputDay16>() {

    private fun processLine(line: String, datalist: MutableMap<String,Valve>, connections: MutableList<Pair<String,String>>) {
        when {
            line.matchRegExp(Regex("""^Valve ([A-Z]+) has flow rate=(\d+); tunnels? leads? to valves? (.*)$""")) -> {
                val (id, flow, connString) = regexMatch!!.destructured
                datalist[id] = Valve( id, flow.toInt())
                connString.split(", ").toList().forEach { connections.add(Pair(id, it)) }
            }
            else -> throw InputDataException("day 16 - bad line: $line")
        }
    }

    override fun process(input: List<String>): InputDay16 {
        val datalist = mutableMapOf<String,Valve>()
        val connections = mutableListOf<Pair<String,String>>()
        input.forEach { line -> processLine(line, datalist, connections) }
        return InputDay16(datalist, connections)
    }
}