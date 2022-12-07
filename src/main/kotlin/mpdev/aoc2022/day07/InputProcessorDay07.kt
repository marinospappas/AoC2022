package mpdev.aoc2022.day07

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay07: InputProcessor<InputDay07>() {

    var regexMatch: MatchResult? = null
    private fun String.matchRegExp(regex: Regex) = regex.find(this).also { regexMatch = it } != null

    override fun process(input: List<String>): InputDay07 {
        val dirStructure = ADirectoryEntry("/", "dir")

        input.forEach { line ->
            when {
                line.matchRegExp(Regex("""^\$ ls$""")) -> {}
                line.matchRegExp(Regex("""^\$ cd /$""")) -> dirStructure.makeRootCurrent()
                line.matchRegExp(Regex("""^\$ cd \.\.$""")) -> dirStructure.makeParentCurrent()
                line.matchRegExp(Regex("""cd ([a-zA-Z\d.\-]+)""")) -> {
                    val (dirName) = regexMatch!!.destructured
                    dirStructure.changeCurDir(dirName)
                }
                line.matchRegExp(Regex("""dir ([a-zA-Z\d.\-]+)""")) -> {
                    val (dirName) = regexMatch!!.destructured
                    dirStructure.createDir(dirName)
                }
                line.matchRegExp(Regex("""(\d+) ([a-zA-Z\d.\-]+)""")) -> {
                    val (fileSize, fileName) = regexMatch!!.destructured
                    dirStructure.createFile(fileName, fileSize.toInt())
                }
            }
        }
        dirStructure.updateDirSizes()
        return InputDay07(dirStructure)
    }
}