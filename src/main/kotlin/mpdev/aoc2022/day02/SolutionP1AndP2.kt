package mpdev.aoc2022.day02

import mpdev.aoc2022.day01.Result

// Input
class PlayData(var player1: Char, var player2: Char) {
    override fun equals(other: Any?): Boolean {
        return other is PlayData && this.player1 == other.player1 && this.player2 == other.player2
    }
    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + player1.code
        hash = hash * 31 + player2.code
        return hash
    }
    override fun toString(): String {
        return "[${player1}, ${player2}]"
    }
}
class Input(var inputList: MutableList<PlayData>)

// Result
class Result(var res: Int = 0)
class Result2(var res: Int = 0)

fun rockPaperScisors(self: Char, opponent: Char): Int {
    /*
    he first column is what your opponent is going to play: A for Rock, B for Paper, and C for Scissors.
    The second column, must be what you should play in response: X for Rock, Y for Paper, and Z for Scissors.
    The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
     plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
     */
    val mySelection = self.code - 'X'.code
    val otherSelection = opponent.code - 'A'.code
    val result: Int
    if (mySelection == otherSelection)  // draw
        result = 3
    else
    if (setOf(1, -2).contains(mySelection - otherSelection))    // win
        result = 6
    else
        result = 0      // lose
    return result + mySelection + 1
}

fun calculateStrategy(self: Char, opponent: Char): Char {
    val myStrategy = self.code - 'X'.code
    val otherSelection = opponent.code - 'A'.code
    val result: Int
    when (myStrategy) {
        1 -> result = otherSelection     // draw
        2 -> result = (otherSelection + 1) % 3  // win
        else -> result = (otherSelection + 2) % 3  // lose
    }
    return (result+'X'.code).toChar()
}

/** part 1 calculation */
fun solvePart1(input: Input): Result {
    return Result(input.inputList.sumOf { it -> rockPaperScisors(it.player2, it.player1) })
}

/** part 2 calculation */
fun solvePart2(input: Input): Result2 {
    return Result2(input.inputList.sumOf { it -> rockPaperScisors(
        calculateStrategy(it.player2, it.player1), it.player1) })
}
