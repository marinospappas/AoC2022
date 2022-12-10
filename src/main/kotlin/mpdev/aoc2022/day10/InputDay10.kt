package mpdev.aoc2022.day10

import java.lang.StringBuilder

const val NOP = "noop"
const val ADD = "addx"
const val PIXELS_PER_LINE = 40
const val NUMBER_OF_LINES = 40

class InputDay10(var instrList: List<Pair<String,String>>) {
    val crt = Crt()
}

class Crt {

    val scanner = mutableListOf(0)     // clock 0 - start
    private val screen = mutableListOf<MutableList<Char>>()

    fun scanScreen(instrList: List<Pair<String, String>>) {
        scanner.add(1) // fist cycle
        instrList.forEach { instr ->
            when (instr.first) {
                NOP -> scanner.add(scanner[scanner.lastIndex])
                ADD -> {
                    scanner.add(scanner[scanner.lastIndex])
                    scanner.add(scanner[scanner.lastIndex] + instr.second.toInt())
                }
            }
        }
    }

    fun draw() {
        (0 until NUMBER_OF_LINES).forEach { line ->
            val screenLine = mutableListOf<Char>()
            (0 until PIXELS_PER_LINE).forEach { pixel ->
                val x = scanner[40 * line + pixel + 1]
                screenLine.add( if (pixel in (x-1)..(x+1)) '#' else '.' )
            }
            screen.add(screenLine)
        }
    }

    override fun toString(): String {
        return StringBuilder().also {
            screen.forEach { line -> it.append(line.joinToString("")).append("\n") }
        }.toString()
    }
}





