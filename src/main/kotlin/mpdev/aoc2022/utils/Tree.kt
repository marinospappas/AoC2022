package mpdev.aoc2022.utils

abstract class TreeNode<T>(var nodaData: T, var parent: TreeNode<T>? = null, var children: MutableList<TreeNode<T>> = mutableListOf()) {

    fun walk(f: (TreeNode<T>) -> Unit) {
        f(this)
        children.forEach { it.walk(f) }
    }

    fun find(condition: (TreeNode<T>) -> Boolean): TreeNode<T>? {
        return this
    }

    fun <K,V>toMap(): Map<K,V> {
        val resMap = mutableMapOf<K,V>()
        return resMap
    }

    fun <V>toList(): List<V> {
        val resList = mutableListOf<V>()
        return resList
    }
}