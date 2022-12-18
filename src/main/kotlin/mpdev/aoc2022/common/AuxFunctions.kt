package mpdev.aoc2022.common

import mpdev.aoc2022.utils.annimation.AnimationObject
import java.io.File
import kotlin.system.exitProcess

var testMode = false
var doAnimation = false
val animationObject = AnimationObject()

/** exit program */
fun exit(msg: String = "") {
    println(msg)
    exitProcess(0)
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

/** get day from args */
fun getAnimation(args: Array<String>): Boolean {
    for (i in args.indices)
        if (args[i] == "-a")
            return true
    return false
}

/** get input from file */
fun getInput(filename: String): List<String> {
    println("input file: $filename")
    return File(filename).readLines()
}
