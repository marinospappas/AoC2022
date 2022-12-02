package mpdev.aoc2022.day02

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

fun calculateStrategy(self: Char, opponent: Char): Char {
    val myStrategy = self.code - 'X'.code
    val otherSelection = opponent.code - 'A'.code
    val result: Int = when (myStrategy) {
        1 -> otherSelection     // draw
        2 -> (otherSelection + 1) % 3  // win
        else -> (otherSelection + 2) % 3  // lose
    }
    return (result+'X'.code).toChar()
}

/** part 1 calculation */
fun solvePart1(input: Input): Result {
    return Result(input.inputList.sumOf { rockPaperScissorsB(it.player2, it.player1) })
}

/** part 2 calculation */
fun solvePart2(input: Input): Result2 {
    return Result2(input.inputList.sumOf { rockPaperScissorsB(calculateStrategy(it.player2, it.player1), it.player1) })
}
