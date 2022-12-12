package mpdev.aoc2022.day11

import java.lang.StringBuilder

class InputDay11(var monkeyList: List<Monkey>) {

    fun processMonkeyOutcome(outcome: List<Pair<Int,Long>>) =
        outcome.forEach { monkeyList[it.first].itemList.add(it.second) }
}

class Monkey (var id: Int, var itemList: MutableList<Long>, var operation: (Long) -> Long, var operationStr: String,
              var divisibleBy: Int, var decision: Pair<Int,Int>) {

    var numberInspected = 0

    fun play(reduce: (Long)-> Long): List<Pair<Int,Long>> { // list of MonkeyId - Item
        val outcome = mutableListOf<Pair<Int,Long>>()
        itemList.forEach {
            val new = reduce(operation(it))
            if (new % divisibleBy == 0L)
                outcome.add(Pair(decision.first, new))
            else
                outcome.add(Pair(decision.second, new))
        }
        numberInspected += itemList.size
        itemList.removeAll { true }
        return outcome
    }

    override fun toString(): String
        = "$id: {[${itemList.joinToString(", ").removeSuffix(",")}] " +
            "$operationStr, divisible by $divisibleBy: T->${decision.first} F->${decision.second}, inspected: $numberInspected}"
}
