package mpdev.aoc2022.common

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
        if (args[i].startsWith("-"))
            when (args[i]) {
                "-part1" -> return 1
                "-part2" -> return 2
            }
    return 0
}

/** get prt 1 or 2 from args */
fun getDay(args: Array<String>): Int {
    for (i in args.indices)
        if (args[i].startsWith("-day"))
            return args[i].substring(4).toInt()
    return 0
}
