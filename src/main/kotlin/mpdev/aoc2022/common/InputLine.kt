package mpdev.aoc2022.common

interface InputLine<S> {
    fun process(firstLine: Boolean, line: String, input: MutableList<S>)
}