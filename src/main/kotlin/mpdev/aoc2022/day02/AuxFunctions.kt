package mpdev.aoc2022.day02

import java.io.File
import kotlin.system.exitProcess

const val AOC = "AoC 2022"
const val AUTHOR = "Marinos Pappas"
const val DATE = "02.12.22"
const val DAY = "Day2"
const val PUZZLE = "Rock Paper Scissors"
const val RESULT_STRING = "Score if I played the recommendation"
const val RESULT_STRING2 = "Score if I played to win/lose/draw as per recommendation"
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
fun processInputLine(firstLine: Boolean, line: String, input: MutableList<PlayData>) {
    val inputData = line.split(" ")
    input.add(PlayData(inputData[0].first(), inputData[1].first()))
}

/** get puzzle input */
fun getInput(args: Array<String>): Input {
    var filename = ""
    val inputData = mutableListOf<PlayData>()
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
