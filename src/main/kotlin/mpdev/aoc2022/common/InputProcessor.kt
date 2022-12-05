package mpdev.aoc2022.common

abstract class InputProcessor<I> {
    /** process input into Input Class */
    abstract fun process(input: List<String>): I
}
