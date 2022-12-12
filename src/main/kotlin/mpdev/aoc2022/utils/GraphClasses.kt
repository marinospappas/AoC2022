package mpdev.aoc2022.utils

interface Vertex<T> {
    fun getId(): T
    fun getConnectedNodes(): List<Vertex<T>>
}

class MinCostPath<T> {
    var path: List<T> = listOf()
    var minCost: Int = Int.MAX_VALUE
    var numberOfIterations: Int = 0
}
