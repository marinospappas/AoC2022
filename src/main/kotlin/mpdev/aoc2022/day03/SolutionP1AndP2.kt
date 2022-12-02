package mpdev.aoc2022.day03

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

/** priorities */
fun getPriority(item: Char): Int {
    return when (item) {
        in 'a'..'z' -> item.code - 'a'.code + 1
        in 'A'..'Z' -> item.code - 'A'.code + 27
        else -> 0
    }
}

/** common items */
fun getCommonItems(s1: String, s2: String): List<Char> {
    val commonItems = mutableListOf<Char>()
    s1.forEach { c -> if (s2.contains(c)) commonItems.add(c) }
    return commonItems.distinct()
}

/** part 1 calculation */
fun solvePart1(input: Input): Result {
    return Result(input.inputList.sumOf {
        getCommonItems(it.comp1, it.comp2).sumOf { c -> getPriority(c) }
    })
}

fun getCommonInGroup(group: List<Rucksack>): Char {
    var commonItems: List<Char> = (group[0].comp1+group[0].comp2).toList()
    for (i in 1 until group.size)
        commonItems = getCommonItems(group[i].comp1+group[i].comp2, commonItems.toString())
    return commonItems[0]
}

fun getGroups(input: Input): Map<Int,List<Rucksack>> {
    return input.inputList.groupBy { input.inputList.indexOf(it) / 3 }
}

/** part 2 calculation */
fun solvePart2(input: Input): Result2 {
    return Result2(getGroups(input).values.sumOf { getPriority(getCommonInGroup(it)) })
}
