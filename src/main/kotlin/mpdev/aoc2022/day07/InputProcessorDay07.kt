package mpdev.aoc2022.day07

import mpdev.aoc2022.common.InputProcessor

class InputProcessorDay07: InputProcessor<InputDay07>() {

    override fun process(input: List<String>): InputDay07 {
        val rootDir = ADirectoryEntry("/", "dir")
        input.forEach {
            when {
                it.matches(Regex("""^\$ cd /$""")) -> rootDir.makeRootCurrent()
                it.matches(Regex("""^\$ ls$""")) -> Unit
                it.matches(Regex("""^\$ cd [a-zA-Z].*$""")) -> changeCurrentDir(it, rootDir)
                it.matches(Regex("""^\$ cd \.\.$""")) -> rootDir.makeParentCurrent()
                it.matches(Regex("""^\$ cd \.\.$""")) -> rootDir.makeParentCurrent()
                it.matches(Regex("""^dir [a-zA-Z].*$""")) -> createDir(it, rootDir)
                it.matches(Regex("""^[0-9]+ [a-zA-Z].*$""")) -> createFile(it, rootDir)
            }
        }
        return InputDay07(rootDir)
    }

    private fun changeCurrentDir(line: String, rootDir: ADirectoryEntry) {
        val match = Regex("""cd ([a-zA-Z\d.\-]+)""").find(line)
        val (dirName) = match!!.destructured
        rootDir.changeCurDir(dirName)
    }

    private fun createDir(line: String, rootDir: ADirectoryEntry) {
        val match = Regex("""dir ([a-zA-Z\d.\-]+)""").find(line)
        val (dirName) = match!!.destructured
        rootDir.createDir(dirName)
    }

    private fun createFile(line: String, rootDir: ADirectoryEntry) {
        val match = Regex("""(\d+) ([a-zA-Z\d.\-]+)""").find(line)
        val (fileSize, fileName) = match!!.destructured
        rootDir.createFile(fileName, fileSize.toInt())
    }
}