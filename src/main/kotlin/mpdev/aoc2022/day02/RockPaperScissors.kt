package mpdev.aoc2022.day02

   /*
   The first column is what your opponent is going to play: A for Rock, B for Paper, and C for Scissors.
   The second column, must be what you should play in response: X for Rock, Y for Paper, and Z for Scissors.
   The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
    plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
    */

/** 1. using if-then-else */
fun rockPaperScissorsA(self: Char, opponent: Char): Int {
    val mySelection = self.code - 'X'.code
    val otherSelection = opponent.code - 'A'.code
    val result: Int = if (mySelection == otherSelection)
        3
    else
    if ((mySelection-otherSelection) == 1 || (mySelection-otherSelection) == -2)
        6
    else
        0
    return result + mySelection + 1
}

/** 2. using maths */
fun rockPaperScissorsB(self: Char, opponent: Char): Int {
    val mySelection = self.code - 'X'.code
    val otherSelection = opponent.code - 'A'.code
    val result: Int = when ((mySelection - otherSelection + 3) % 3) {
        0 -> 3  // draw
        1 -> 6  // win
        else -> 0   // lose
    }
    return result + mySelection + 1
}

/** 3. using map */
val score = mapOf(
    "AX" to 3+1,
    "BX" to 0+1,
    "CX" to 6+1,
    "AY" to 6+2,
    "BY" to 3+2,
    "CY" to 0+2,
    "AZ" to 0+3,
    "BZ" to 6+3,
    "CZ" to 3+3,
)
fun rockPaperScissorsC(self: Char, opponent: Char): Int {
    return score[opponent.toString()+self.toString()]!!
}