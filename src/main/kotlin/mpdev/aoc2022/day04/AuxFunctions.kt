package mpdev.aoc2022.day04

import java.io.File
import kotlin.system.exitProcess

const val AOC = "AoC 2022"
const val AUTHOR = "Marinos Pappas"
const val DATE = "04.12.22"
const val DAY = "Day4"
const val PUZZLE = "..."
const val RESULT_STRING = "xxx"
const val RESULT_STRING2 = "xxx"
const val USAGE = "usage: Main -part1|-part2 Input_File"

/** exit program */
fun exit(msg: String) {
    println(msg)
    exitProcess(0)
}

/** abort */
fun abort(errMsg: String) {
    System.err.println(errMsg)
    exitProcess(1)
}

/** get prt 1 or 2 from args */
fun getPart1or2(args: Array<String>): Int {
    for (i in args.indices)
        if (args[i].startsWith("-"))
            when (args[i]) {
                "-part1" -> return 1
                "-part2" -> return 2
            }
    return 0
}

/** process 1 input line */
fun processInputLine(firstLine: Boolean, line: String, input: MutableList<Rucksack>) {
    val midLength = line.length / 2
    input.add(Rucksack("",""))
}

/** get puzzle input */
fun getInput(args: Array<String>): Input {
    var filename = ""
    val inputData = mutableListOf<Rucksack>()
    for (i in args.indices) {
        if (args[i].startsWith("-"))
            continue
        filename = args[i]
        break
    }
    if (filename == "") abort(USAGE)
    println("input file: $filename")
    var firstLine = true
    try {
        File(filename).forEachLine {
            processInputLine(firstLine, it, inputData)
            firstLine = false
        }
    }
    catch (e: Exception) {
        abort(e.toString())
    }
    return Input(inputData)
}
