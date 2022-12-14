package mpdev.aoc2022.day08

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay08: InputProcessor<InputDay08>() {

    var regexMatch: MatchResult? = null
    private fun String.matchRegExp(regex: Regex) = regex.find(this).also { regexMatch = it } != null

    private fun processLine(line: String, treeGrid: MutableList<List<Int>>) =
        treeGrid.add(mutableListOf<Int>().also { line.forEach { char -> it.add(char.digitToInt()) } })

    override fun process(input: List<String>): InputDay08 {
        val treeGrid: MutableList<List<Int>> = mutableListOf()
        input.forEach { line -> processLine(line, treeGrid)}
        return InputDay08(treeGrid, treeGrid.size, treeGrid[0].size)
    }
}