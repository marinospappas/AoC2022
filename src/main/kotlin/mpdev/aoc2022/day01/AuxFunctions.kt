package mpdev.aoc2022.day01

import java.io.File
import kotlin.system.exitProcess

const val AOC = "AoC 2022"
const val AUTHOR = "Marinos Pappas"
const val DATE = "01.12.22"
const val DAY = "Day1"
const val PUZZLE = "Calorie Counting"
const val RESULT_STRING = "Calories carried by the Elf with the most calories"
const val RESULT_STRING2 = "Total calories carried by the top 3 Elves"
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
var elfId = 0
fun processInputLine(firstLine: Boolean, line: String, input: MutableList<Elf>) {
    if (line.isNotEmpty())
        input.add(Elf(elfId, line.toInt()))
    else
        ++elfId
}

/** get puzzle input */
fun getInput(args: Array<String>): Input {
    var filename = ""
    val inputData = mutableListOf<Elf>()
    for (i in args.indices) {
        if (args[i].startsWith("-"))
            continue
        filename = args[i]
        break
    }
    if (filename == "") abort(USAGE)
    println("input file: $filename")
    var firstLine = true
    elfId = 0
    try {
        File(filename).forEachLine { processInputLine(firstLine, it, inputData); firstLine=false }
    }
    catch (e: Exception) {
        abort(e.toString())
    }
    return Input(inputData)
}
