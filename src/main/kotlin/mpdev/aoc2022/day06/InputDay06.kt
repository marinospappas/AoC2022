package mpdev.aoc2022.day06

class InputDay06(var stacks: MutableList<String> = mutableListOf(), var moves: List<Move> = listOf() ) {

    override fun toString(): String {
        var out1 = ""
        for (i in 1..stacks.size)
            out1 += "$i: ${stacks[i-1]}\n"
        var out2 = ""
        moves.forEach { out2 += it.toString() }
        return "$out1\n$out2"
    }
}