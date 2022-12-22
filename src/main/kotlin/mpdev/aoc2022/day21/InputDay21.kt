package mpdev.aoc2022.day21

import java.math.BigInteger

class InputDay21(val monkeyMap: MutableMap<String,Monkey>, val parent: Map<String,Pair<String,Boolean>>) {

    init {
        // update the calc function for each monkey
        updateCalcFunction(monkeyMap)
    }

    private fun updateCalcFunction(map: Map<String,Monkey>) {
        map.values.forEach {
            when (it.op) {
                '+' -> {
                    it.calc = { s1,s2 -> map[s1]!!.getResult() + map[s2]!!.getResult() }
                }
                '-' -> {
                    it.calc = { s1,s2 -> map[s1]!!.getResult() - map[s2]!!.getResult() }
                }
                '*' -> {
                    it.calc = { s1,s2 -> map[s1]!!.getResult() * map[s2]!!.getResult() }
                }
                '/' -> {
                    it.calc = { s1,s2 -> map[s1]!!.getResult() / map[s2]!!.getResult() }
                }
            }
            // also rev calc
            val pid = parent[it.id] ?: return@forEach
            when (map[pid.first]!!.op) {
                '+' -> {
                    it.revCalc = { s1,s2 ->
                        if (pid.second)     // if on the left hand side of the parent equation
                            map[pid.first]!!.getRevResult() - map[s2]!!.getResult()
                        else
                            map[pid.first]!!.getRevResult() - map[s1]!!.getResult()
                    }
                }
                '-' -> {
                    it.revCalc = { s1,s2 ->
                        if (pid.second)     // if on the left hand side of the parent equation
                            map[pid.first]!!.getRevResult() + map[s2]!!.getResult()
                        else
                            map[s1]!!.getResult() - map[pid.first]!!.getRevResult()
                    }
                }
                '*' -> {
                    it.revCalc = { s1,s2 ->
                        if (pid.second)     // if on the left hand side of the parent equation
                            map[pid.first]!!.getRevResult() / map[s2]!!.getResult()
                        else
                            map[pid.first]!!.getRevResult() / map[s1]!!.getResult()
                    }
                }
                '/' -> {
                    it.revCalc = { s1,s2 ->
                        if (pid.second)     // if on the left hand side of the parent equation
                            map[pid.first]!!.getRevResult() * map[s2]!!.getResult()
                        else
                            map[s1]!!.getResult() / map[pid.first]!!.getRevResult()
                    }
                }
            }
        }
    }

    class Monkey(val id: String, val s1: String = "", val s2: String = "", val op: Char = ' ', val number: BigInteger = BigInteger("0")) {
        var calc: (String, String) -> BigInteger? = { _,_ -> null }
        var revCalc: (String, String) -> BigInteger? = { _,_ -> null }
        fun getResult() = calc(s1, s2) ?: number
        fun getRevResult() = revCalc(s1, s2) ?: number
        override fun toString() = if (op == ' ') number.toString() else "$id = $s1 $op $s2"
    }
}
