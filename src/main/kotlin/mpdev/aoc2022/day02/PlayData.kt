package mpdev.aoc2022.day02

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