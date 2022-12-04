package mpdev.aoc2022.day04

class Section(var start: Int, var end: Int) {
    override fun equals(other: Any?): Boolean {
        return other is Section && this.start == other.start && this.end == other.end
    }
    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + start
        hash = hash * 31 + end
        return hash
    }
    override fun toString(): String {
        return "[$start, $end]"
    }

    fun contains(other: Section): Boolean {
        return other.start >= this.start && other.start <= this.end
                && other.end >= this.start && other.end <= this.end
    }

    fun overlaps(other: Section): Boolean {
        return other.start >= this.start && other.start <= this.end
                || other.end >= this.start && other.end <= this.end
    }
}