package mpdev.aoc2022.day05

class GroupedSections(var sectionList: List<String>) {
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