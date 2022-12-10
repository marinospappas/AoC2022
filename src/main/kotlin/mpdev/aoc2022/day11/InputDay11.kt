package mpdev.aoc2022.day11

import java.lang.StringBuilder

class InputDay11(var instrList: List<Pair<String,String>>) {
    val crt = Crt()
}

class Crt {

    private val screen = mutableListOf<MutableList<Char>>()

    override fun toString(): String {
        return StringBuilder().also {
            screen.forEach { line -> it.append(line.joinToString("")).append("\n") }
        }.toString()
    }
}
