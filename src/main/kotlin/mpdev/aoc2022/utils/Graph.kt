package mpdev.aoc2022.utils

class GraphNode<T: GraphNodeData<U>, U> : MinCostGraphNode<U>() {

    var nodeData: T? = null
    var neighbours: MutableList<GraphNode<T,U>> = mutableListOf()

    fun addNode(node: GraphNode<T,U>) {
        node.neighbours.add(this)
        neighbours.add(node)
    }

    override fun getId(): U {
        TODO("Not yet implemented")
    }


    override fun getConnectedNodes(): List<MinCostNodeWithCost<U>> {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        var out = nodeData.toString() + "neighbours\n"
        neighbours.forEach { out += "  ${it.nodeData.toString()}" }
        return out
    }
}