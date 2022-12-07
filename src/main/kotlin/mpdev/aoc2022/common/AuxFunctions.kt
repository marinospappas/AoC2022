package mpdev.aoc2022.common

import java.io.File
import kotlin.system.exitProcess

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
        if (args[i].startsWith("-part"))
            return when (args[i]) {
                "-part1" -> 1
                "-part2" -> 2
                else -> -1
            }
    return 0
}

/** get day from args */
fun getDay(args: Array<String>): Int {
    for (i in args.indices)
        if (args[i].startsWith("-day"))
            return args[i].substring(4).toInt()
    return 0
}

/** get input from file */
fun getInput(filename: String): List<String> {
    println("input file: $filename")
    try {
        return File(filename).readLines()
    }
    catch (e: Exception) {
        abort(e.toString())
    }
    return emptyList()
}
