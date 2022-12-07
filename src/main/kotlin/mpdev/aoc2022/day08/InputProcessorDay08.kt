package mpdev.aoc2022.day08

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay08: InputProcessor<InputDay08>() {

    var regexMatch: MatchResult? = null
    private fun String.matchRegExp(regex: Regex) = regex.find(this).also { regexMatch = it } != null

    override fun process(input: List<String>): InputDay08 {
        val dirStructure = ADirectoryEntry("/", "dir")

        input.forEach { line ->
            when {                        // line "$ ls" is ignored
                line.matchRegExp(Regex("""^\$ cd (.+)$""")) -> {
                    val (dirName) = regexMatch!!.destructured
                    dirStructure.changeCurDir(dirName)
                }
                line.matchRegExp(Regex("""^dir ([a-zA-Z]+[a-zA-Z\d._\-]*)$""")) -> {
                    val (dirName) = regexMatch!!.destructured
                    dirStructure.createDir(dirName)
                }
                line.matchRegExp(Regex("""^(\d+) ([a-zA-Z]+[a-zA-Z\d._\-]*)$""")) -> {
                    val (fileSize, fileName) = regexMatch!!.destructured
                    dirStructure.createFile(fileName, fileSize.toInt())
                }
            }
        }
        dirStructure.updateDirSizes()
        return InputDay08(dirStructure)
    }
}