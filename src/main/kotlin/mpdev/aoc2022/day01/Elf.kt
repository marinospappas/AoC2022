package mpdev.aoc2022.day01

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
