package mpdev.aoc2022.day01

class Elf(var id: Int, var calories: Int = 0)

class Input(var inputList: MutableList<Elf>)

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
