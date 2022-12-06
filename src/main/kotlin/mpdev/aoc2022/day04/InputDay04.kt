package mpdev.aoc2022.day04

open class InputDay04(var inputList: MutableList<GroupedSections> = mutableListOf())

/** GroupedSections class */
class GroupedSections(var sectionList: List<Pair<Int,Int>>) {
    override fun equals(other: Any?): Boolean {
        return other is GroupedSections && this.sectionList == other.sectionList
    }
    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + sectionList.hashCode()
        return hash
    }
    override fun toString(): String {
        return "$sectionList"
    }
}