package mpdev.aoc2022.day01

// input
class Elf(var id: Int, var calories: Int = 0) {
    override fun equals(other: Any?): Boolean {
        return other is Elf && this.id == other.id && this.calories == other.calories
    }
    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + id
        hash = hash * 31 + calories
        return hash
    }
    override fun toString(): String {
        return "[${id}, ${calories}]"
    }
}

class Input(var inputList: MutableList<Elf>)

// Result
class Result(var res: Int = 0)
class Result2(var res: Int = 0)

/** part 1 calculation */
fun solvePart1(input: Input): Result {
    input.inputList.sortBy { it.calories }
    return Result(input.inputList.last().calories)
}

/** part 2 calculation */
fun solvePart2(input: Input): Result2 {
    input.inputList.sortBy { it.calories }
    val myList = input.inputList
    val last = myList.lastIndex
    return Result2(
        myList[last].calories + myList[last - 1].calories + myList[last - 2].calories
    )
}
