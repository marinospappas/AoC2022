package mpdev.aoc2022.day07

import mpdev.aoc2022.utils.TreeNode

class InputDay07(var root: ADirectoryEntry) {

    override fun toString(): String {
        return root.toString()
    }
}

var FILE = "file"
var DIR = "dir"
var seqNum: Int = 0
lateinit var curDir: TreeNode<Node>

/** Directory entry class - type can be either File or Directory */
class Node(var name: String, var type: String, var inode: Int, var size: Int = 0) {

    override fun toString(): String { val padding = ""
        return if (type == FILE)
            "$padding- $name (file, size=$size, inode=$inode)\n"
        else
            "$padding- $name (dir, parent=$name, total size=$size, inode=$inode)\n"
    }
}

class ADirectoryEntry(nodeData: Node): TreeNode<Node>(nodeData) {

    fun changeCurDir(name: String) {
        when (name) {
            "/" -> curDir = this        // must be called at the top of the tree (root dir)
            ".." -> curDir = curDir.parent!!
            else -> curDir.children.stream().filter { it.nodeData.name == name && it.nodeData.type == DIR }
                .toList().first().also { curDir = it }
        }
    }

    fun createDir(name: String) {
        curDir.addChild(Node(name, DIR, ++seqNum))
    }

    fun createFile(name: String, size: Int) {
        curDir.addChild(Node(name, FILE, ++seqNum, size))
    }

    fun getDirSize(dir: TreeNode<Node>): Int {
        return dir.sumOf({ n -> n.nodeData.size }, { n -> n.nodeData.type == FILE })
    }

    fun updateDirSizes() {
        this.walk({ n -> n.nodeData.size = getDirSize(n) }, { n -> n.nodeData.type == DIR } )
    }

    fun getDirSizes(): List<Int> {
        return this.toList({ n -> n.nodeData.size }, { n -> n.nodeData.type == DIR })
    }

}