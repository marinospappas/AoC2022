package mpdev.aoc2022.utils

abstract class GraphNodeData<T> {
    abstract fun getId(): T?
}

interface MinCostNodeWithCost<T> {
    var node: MinCostGraphNode<T>
    var costFromPrev: Int
}

abstract class MinCostGraphNode<T> {
    abstract fun getId(): T
    abstract fun getConnectedNodes(): List<MinCostNodeWithCost<T>>
    open fun heuristic() = 0
}

class MinCostPath<T> {
    var path: List<T> = listOf()
    var minCost: Int = Int.MAX_VALUE
    var numberOfIterations: Int = 0
}
