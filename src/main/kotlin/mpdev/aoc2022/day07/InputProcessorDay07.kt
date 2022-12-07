package mpdev.aoc2022.day07

import mpdev.aoc2022.common.InputProcessor
import mpdev.aoc2022.common.abort
import java.lang.Exception

class InputProcessorDay07: InputProcessor<InputDay07>() {

    override fun process(input: List<String>): InputDay07 {
        val rootDir = ADirectoryEntry("/", "dir", null, true)
        input.forEach {
            if (it.matches(Regex("""^\$ cd /$""")))
                ; // cd / is ignored as it only appears in the beginning
            else
            if (it.matches(Regex("""^\$ ls$""")))
                ; // ls is ignored
            else
            if (it.matches(Regex("""^\$ cd [a-zA-Z].*$""")))
                changeCurrentDir(it, rootDir)
            if (it.matches(Regex("""^\$ cd \.\.$""")))
                rootDir.makeParentCurrent()
            else
            if (it.matches(Regex("""^dir [a-zA-Z].*$""")))
                createDir(it, rootDir)
            else
            if (it.matches(Regex("""^[0-9]+ [a-zA-Z].*$""")))
                createFile(it, rootDir)
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