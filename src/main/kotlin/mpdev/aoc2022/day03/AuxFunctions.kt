package mpdev.aoc2022.day03

import java.io.File
import kotlin.system.exitProcess

const val AOC = "AoC 2022"
const val AUTHOR = "Marinos Pappas"
const val DATE = "03.12.22"
const val DAY = "Day3"
const val PUZZLE = "Rucksack Reorganization"
const val RESULT_STRING = "Sum of priorities of items common across compartments"
const val RESULT_STRING2 = "Sum of priorities of items common in group"
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
    if (line.length % 2 != 0)
        mpdev.aoc2022.day02.abort("bad input line $line")
    val midLength = line.length / 2
    input.add(Rucksack(line.substring(0,midLength), line.substring(midLength)))
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
