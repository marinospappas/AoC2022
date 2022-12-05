package mpdev.aoc2022.utils

interface NodeCost<T> {
    var node: GraphNode<T>
    var costFromPrev: Int
}

interface GraphNode<T> {
    var id: T
    fun getConnectedNodes(): List<NodeCost<T>>
    fun heuristic(): Int
}

class MinCostPath<T> {
    var path: List<T> = listOf()
    var minCost: Int = Int.MAX_VALUE
    var numberOfIterations: Int = 0
}
