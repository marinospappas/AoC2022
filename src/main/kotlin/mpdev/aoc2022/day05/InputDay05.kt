package mpdev.aoc2022.day05

class InputDay05(var stacks: MutableList<String> = mutableListOf(), var moves: List<Move> = listOf() ) {

    override fun toString(): String {
        var out1 = ""
        for (i in 1..stacks.size)
            out1 += "$i: ${stacks[i-1]}\n"
        var out2 = ""
        moves.forEach { out2 += it.toString() }
        return "$out1\n$out2"
    }
}

/** Move class */
class Move(var count: Int, var src: Int, var dest: Int) {

    override fun equals(other: Any?): Boolean {
        return other is Move && this.count == other.count && this.src == other.src && this.dest == other.dest
    }

    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + count
        hash = hash * 31 + src
        hash = hash * 31 + dest
        return hash
    }

    override fun toString(): String {
        return "move ${count} from ${src} to ${dest}\n"
    }
}
