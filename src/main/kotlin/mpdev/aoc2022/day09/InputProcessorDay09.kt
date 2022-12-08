package mpdev.aoc2022.day09

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay09: InputProcessor<InputDay09>() {

    var regexMatch: MatchResult? = null
    private fun String.matchRegExp(regex: Regex) = regex.find(this).also { regexMatch = it } != null

    private fun processLine(line: String, dataList: MutableList<List<Int>>) =
        dataList.add(mutableListOf<Int>().also { line.forEach { char -> it.add(char.digitToInt()) } })

    override fun process(input: List<String>): InputDay09 {
        val dataList: MutableList<List<Int>> = mutableListOf()
        input.forEach { line -> processLine(line, dataList)}
        return InputDay09(dataList, dataList.size, dataList[0].size)
    }
}