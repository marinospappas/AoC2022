package mpdev.aoc2022.day21

import mpdev.aoc2022.common.RunTimeException
import java.math.BigInteger

class Day21(val monkeyMap: MutableMap<String,Monkey>, val parent: MutableMap<String,Pair<String,Boolean>>) {

    init {
        // update the calc function for each monkey
        updateCalcFunction(monkeyMap)
        // also update the reverse calc function (part 2)
        updateRevCalcFunction(monkeyMap)
    }

    private fun updateCalcFunction(map: Map<String,Monkey>) {
        map.values.forEach {
            when (it.op) {
                '+' -> it.calc = { s1, s2 -> map[s1]!!.getResult() + map[s2]!!.getResult() }
                '-' -> it.calc = { s1, s2 -> map[s1]!!.getResult() - map[s2]!!.getResult() }
                '*' -> it.calc = { s1, s2 -> map[s1]!!.getResult() * map[s2]!!.getResult() }
                '/' -> it.calc = { s1, s2 -> map[s1]!!.getResult() / map[s2]!!.getResult() }
            }
        }
    }

    private fun updateRevCalcFunction(map: Map<String,Monkey>) {
        map.values.forEach {
            // also rev calc
            val pid = parent[it.id] ?: return@forEach
            val pMonkey = map[pid.first] ?: throw RunTimeException("could not retrieve parent object for id ${pid.first}")
            when (pMonkey.op) {   // the reverse calc depends on whether the object is on the left or on the right in the parent calc
                '+' -> it.revCalc = if (pid.second) { _, _ -> pMonkey.getRevResult() - map[pMonkey.s2]!!.getResult() }
                                else { _, _ -> pMonkey.getRevResult() - map[pMonkey.s1]!!.getResult() }
                '-' -> it.revCalc = if (pid.second)  { _, _ -> pMonkey.getRevResult() + map[pMonkey.s2]!!.getResult() }
                                else { _, _ -> map[pMonkey.s1]!!.getResult() - pMonkey.getRevResult() }
                '*' -> it.revCalc = if (pid.second) { _, _ -> pMonkey.getRevResult() / map[pMonkey.s2]!!.getResult() }
                                else { _, _ -> pMonkey.getRevResult() / map[pMonkey.s1]!!.getResult() }
                '/' -> it.revCalc = if (pid.second) { _, _ -> pMonkey.getRevResult() * map[pMonkey.s2]!!.getResult() }
                                else { _, _ -> map[pMonkey.s1]!!.getResult() / pMonkey.getRevResult() }
            }
        }
    }

    fun calcSideOfHuman(): Boolean {
        var id = "humn"
        while (parent[id]!!.first != "root") {
            id = parent[id]!!.first
        }
        return parent[id]!!.second
    }

    class Monkey(val id: String, val s1: String = "", val s2: String = "", val op: Char = ' ', val number: BigInteger = BigInteger("0")) {
        var calc: (String, String) -> BigInteger? = { _,_ -> null }
        var revCalc: (String, String) -> BigInteger? = { _,_ -> null }
        fun getResult() = calc(s1, s2) ?: number
        fun getRevResult() = revCalc(s1, s2) ?: number
        override fun toString() = "$id = " + (if (op == ' ') number.toString() else "$s1 $op $s2")
    }
}
