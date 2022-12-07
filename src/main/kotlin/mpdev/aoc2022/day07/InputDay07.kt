package mpdev.aoc2022.day07

import java.lang.StringBuilder

class InputDay07(var root: ADirectoryEntry) {

    override fun toString(): String {
        return root.toString()
    }
}

var seqNum: Int = 0

/** Directory entry class - type can be either File or Directory */
class ADirectoryEntry(var name: String, var type: String, var parent: ADirectoryEntry? = null, var isCurrent: Boolean = false,
                      var entries: MutableList<ADirectoryEntry> = mutableListOf(), var size: Int = 0) {

    var inode = seqNum++;

    fun findCurrent(): ADirectoryEntry? {
        if (type == "dir" && isCurrent)
            return this
        else
            entries.stream().filter { it.type == "dir" }.toList().forEach {
                val cur = it.findCurrent()
                if (cur != null)
                    return cur
            }
        return null
    }

    fun makeParentCurrent() {
        val curDir = findCurrent()!!
        curDir.isCurrent = false
        curDir.parent?.isCurrent = true
    }

    fun changeCurDir(name: String) {
        val curDir = findCurrent()!!
        curDir.entries.stream().filter { it.name == name && it.type == "dir" }.toList().first().isCurrent = true
        curDir.isCurrent = false
    }

    fun createDir(name: String) {
        val curDir = findCurrent()!!
        curDir.entries.add(ADirectoryEntry(name, "dir", curDir))
    }

    fun createFile(name: String, size: Int) {
        val curDir = findCurrent()!!
        curDir.entries.add(ADirectoryEntry(name, "file", size = size))
    }

    fun getFileSizes(): List<Int> {
        val sizes = mutableListOf<Int>()
        entries.stream().filter { it.type == "file" }.toList().forEach { sizes.add(it.size) }
        entries.stream().filter { it.type == "dir" }.toList().forEach { sizes.addAll(it.getFileSizes()) }
        return sizes
    }

    fun getDirSize(): Int {
        var size: Int
        size = entries.stream().filter { it.type == "file" }.toList().sumOf{it.size}
        entries.stream().filter { it.type == "dir" }.toList().forEach { size += it.getDirSize() }
        return size
    }

    fun updateDirSizes() {
        size = getDirSize()
        entries.stream().filter { it.type == "dir" }.toList().forEach { it.updateDirSizes() }
    }

    fun getDirSizes(): Map<String, Int> {
        val sizes = mutableMapOf<String, Int>()
        sizes["$name/$inode"] = size
        entries.stream().filter { it.type == "dir" }.toList().forEach { sizes.putAll(it.getDirSizes()) }
        return sizes
    }

    override fun equals(other: Any?): Boolean {
        return other is ADirectoryEntry && this.name == other.name && this.isCurrent == other.isCurrent &&
                this.parent == other && this.entries == other.entries && this.size == other.size
    }

    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + name.hashCode()
        hash = hash * 31 + type.hashCode()
        hash = hash * 31 + isCurrent.hashCode()
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
            out = "$padding- $name (dir, parent=${parent?.name} current=$isCurrent, total size=$size, inode=$inode)\n"
            out += StringBuilder().also {
                entries.forEach { entry -> it.append(entry.toString("$padding  ")) }
            }
        }
        return out
    }
}