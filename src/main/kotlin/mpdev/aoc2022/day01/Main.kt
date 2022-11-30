package mpdev.aoc2022.day01

class Input(inputList: MutableList<String>)
class Result(res: Int)
class Result2(res: Int)

/** part 1 calculation */
fun solvePart1(input: Input): Result {
    var result = Result(0)
    return result
}

/** part 2 calculation */
fun solvePart2(input: Input): Result2 {
    var result = Result2(0)
    return result
}

fun main(args: Array<String>) {

    val part1_2 = getPart1or2(args)
    if (part1_2 == 0) abort(USAGE)
    println("$AOC - $DAY, $PUZZLE, Part $part1_2 - $AUTHOR - $DATE")

    val input = getInput(args)
    if (part1_2 == 1) {
        val result = solvePart1(input)
        println("$RESULT_STRING: $result")
    }
    else {
        val result2 = solvePart2(input)
        println("$RESULT_STRING2: $result2")
    }
    exit("$DAY Part $part1_2 - Completed")
}