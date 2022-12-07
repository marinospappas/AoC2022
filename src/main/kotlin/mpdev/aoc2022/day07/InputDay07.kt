package mpdev.aoc2022.day07

import java.lang.StringBuilder

class InputDay07(var root: ADirectoryEntry) {

    override fun toString(): String {
        return root.toString()
    }
}

var seqNum: Int = 0
lateinit var curDir: ADirectoryEntry

/** Directory entry class - type can be either File or Directory */
class ADirectoryEntry(var name: String, var type: String, var parent: ADirectoryEntry? = null,
                      var entries: MutableList<ADirectoryEntry> = mutableListOf(), var size: Int = 0) {

    var inode = seqNum++

    fun makeRootCurrent() {
        // must be called at the top of the tree (root dir)
        curDir = this
    }

    fun makeParentCurrent() {
        curDir = curDir.parent!!
    }

    fun changeCurDir(name: String) {
        curDir.entries.stream().filter { it.name == name && it.type == "dir" }.toList().first()
            .also { curDir = it }
    }

    fun createDir(name: String) {
        curDir.entries.add(ADirectoryEntry(name, "dir", curDir))
    }

    fun createFile(name: String, size: Int) {
        curDir.entries.add(ADirectoryEntry(name, "file", size = size))
    }

    fun getDirSize(): Int {
        return entries.stream().filter { it.type == "file" }.toList().sumOf{ it.size } +
                entries.stream().filter { it.type == "dir" }.toList().sumOf{ it.getDirSize() }
    }

    fun updateDirSizes() {
        size = getDirSize()
        entries.stream().filter { it.type == "dir" }.toList().forEach { it.updateDirSizes() }
    }

    fun getDirSizes(): Map<String, Int> {
        val sizes = mutableMapOf<String, Int>()
        sizes["${name}_$inode"] = size
        entries.stream().filter { it.type == "dir" }.toList().forEach { sizes.putAll(it.getDirSizes()) }
        return sizes
    }

    override fun equals(other: Any?): Boolean {
        return other is ADirectoryEntry && this.name == other.name &&
                this.parent == other && this.entries == other.entries && this.size == other.size
    }

    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + name.hashCode()
        hash = hash * 31 + type.hashCode()
        hash = hash * 31 + parent.hashCode()
        hash = hash * 31 + entries.hashCode()
        return hash
    }

    override fun toString() = toString("")

    private fun toString(padding: String): String {
        var out = ""
        if (type == "file") {
            out = "$padding- $name (file, size=$size, inode=$inode)\n"
        }
        else
        if (type == "dir") {
            out = "$padding- $name (dir, parent=${parent?.name}, total size=$size, inode=$inode)\n"
            out += StringBuilder().also {
                entries.forEach { entry -> it.append(entry.toString("$padding  ")) }
            }
        }
        return out
    }
}