package mpdev.aoc2022.day05

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