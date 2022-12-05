package mpdev.aoc2022.day04

/* class Pair extensions */
fun Pair<Int,Int>.contains(other: Pair<Int,Int>): Boolean {
    return other.first >= this.first && other.first <= this.second
            && other.second >= this.first && other.second <= this.second
}

fun Pair<Int,Int>.overlaps(other: Pair<Int,Int>): Boolean {
    return other.first >= this.first && other.first <= this.second
            || other.second >= this.first && other.second <= this.second
}