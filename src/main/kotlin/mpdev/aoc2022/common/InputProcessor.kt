package mpdev.aoc2022.common

interface InputProcessor<T> {
    /** process input into Input Class */
    fun process(input: List<String>): Input<T>
}
