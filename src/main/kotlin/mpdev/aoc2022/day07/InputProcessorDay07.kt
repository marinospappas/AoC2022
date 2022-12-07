package mpdev.aoc2022.day07

import mpdev.aoc2022.common.InputProcessor

var regexMatch: MatchResult? = null
fun String.matchRegExp(regexp: String) = regexp.toRegex().find(this).also { regexMatch = it } != null

class InputProcessorDay07: InputProcessor<InputDay07>() {

    override fun process(input: List<String>): InputDay07 {
        val rootDir = ADirectoryEntry("/", "dir")

        input.forEach { line ->
            when {
                line.matchRegExp("""^\$ ls$""") -> {}
                line.matchRegExp("""^\$ cd /$""") -> rootDir.makeRootCurrent()
                line.matchRegExp("""^\$ cd \.\.$""") -> rootDir.makeParentCurrent()
                line.matchRegExp("""cd ([a-zA-Z\d.\-]+)""") -> {
                    val (dirName) = regexMatch!!.destructured
                    rootDir.changeCurDir(dirName)
                }
                line.matchRegExp("""dir ([a-zA-Z\d.\-]+)""") -> {
                    val (dirName) = regexMatch!!.destructured
                    rootDir.createDir(dirName)
                }
                line.matchRegExp("""(\d+) ([a-zA-Z\d.\-]+)""") -> {
                    val (fileSize, fileName) = regexMatch!!.destructured
                    rootDir.createFile(fileName, fileSize.toInt())
                }
            }
        }
        rootDir.updateDirSizes()
        return InputDay07(rootDir)
    }
}