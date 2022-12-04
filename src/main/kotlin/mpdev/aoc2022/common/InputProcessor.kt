package mpdev.aoc2022.common

abstract class InputProcessor<T> {
    /** process input into Input Class */
    abstract fun processLine(inputLine: String, dataList: MutableList<T>)

    fun process(input: List<String>): Input<T> {
        val dataList = mutableListOf<T>()
        input.forEach { processLine(it, dataList) }
        return Input(dataList)
    }
}
