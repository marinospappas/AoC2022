package mpdev.aoc2022.utils

import java.lang.StringBuilder

class Graph<T> {

    private val nodes = mutableMapOf<T, GraphNode<T>>()

    val costs = mutableMapOf<Pair<T,T>,Int>()
    val heuristics = mutableMapOf<T,Int>()

    operator fun get(id: T) = nodes[id] ?: throw IllegalArgumentException()

    fun addNode(id: T) {
        nodes[id] = GraphNode(id)
    }

    fun connect(first: T, second: T) = connect(this[first], this[second])

    private fun connect(first: GraphNode<T>, second: GraphNode<T>) {
        if(!first.neighbours.contains(second))
            first.neighbours.add(second)
    }

    fun updateHeuristic(id: T, heuristic: Int) {
        heuristics[id] = heuristic
    }

    fun updateCost(fromId: T, toId: T, cost: Int) {
        costs[Pair(fromId,toId)] = cost
    }

    private fun getNeighbours(id: T) = nodes[id]?.getConnectedNodes()

    override fun toString(): String {
        return StringBuilder().also { s ->
            nodes.keys.forEach { id ->
                s.append("id: $id, connects to ").also {
                    getNeighbours(id)?.forEach { n -> s.append("  ${n.getId()}, cost: ${costs[Pair(id, n.getId())]}")
                        .append("heuristic: ${heuristics[id]}") }
                }.also { s.append("\n") }
            }
        }
            .append("heuristics: $heuristics")
            .toString()
    }
}

class GraphNode<T>(var nodeId: T): Vertex<T> {

    val neighbours = mutableListOf<GraphNode<T>>()

    override fun getId() = nodeId

    override fun getConnectedNodes(): List<GraphNode<T>> = neighbours

}

