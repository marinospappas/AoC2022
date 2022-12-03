package mpdev.aoc2022.common

interface InputLine<T> {
    fun process(firstLine: Boolean, line: String, input: MutableList<T>)
}