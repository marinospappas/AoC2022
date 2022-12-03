package mpdev.aoc2022.day04

// Input
class Rucksack(var comp1: String, var comp2: String) {
    override fun equals(other: Any?): Boolean {
        return other is Rucksack && this.comp1 == other.comp1 && this.comp2 == other.comp2
    }
    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + comp1.hashCode()
        hash = hash * 31 + comp2.hashCode()
        return hash
    }
    override fun toString(): String {
        return "[${comp1}, ${comp2}]"
    }
}
class Input(var inputList: MutableList<Rucksack>)

// Result
class Result(var res: Int = 0)
class Result2(var res: Int = 0)


/** part 1 calculation */
fun solvePart1(input: Input): Result {
    return Result()
}

/** part 2 calculation */
fun solvePart2(input: Input): Result2 {
    return Result2()
}
