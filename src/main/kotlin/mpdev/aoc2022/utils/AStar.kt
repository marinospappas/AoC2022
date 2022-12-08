package mpdev.aoc2022.utils

import java.util.*

/**
 * A* implementation
 * T is the type of the Node ID in the Graph
 */
class AStar<T> {

    class PathNode<T>(
        val node: MinCostGraphNode<T>?,
        var costFromStart: Int,
        var estTotCostToEnd: Int,
        var updatedBy: T? = null): Comparable<PathNode<T>> {
        override fun compareTo(other: PathNode<T>): Int {
            // in A* min cost criteria is based on total estimated cost via this path
            return estTotCostToEnd.compareTo(other.estTotCostToEnd)
        }

        override fun toString(): String {
            return "\nnode: $node cost from start $costFromStart updated by $updatedBy"
        }
    }

    class AStarException(override var message: String): Exception()

    /**
     * The A* algorithm - improves the sesrch by adding estimated cost to end
     */
    fun runIt(startState: MinCostGraphNode<T>, endState: MinCostGraphNode<T>): MinCostPath<T> {

        // setup priority queue, visited set and minimum total costs for each node
        val toVisitQueue = PriorityQueue<PathNode<T>>().apply { add(PathNode(startState,0, 0)) }
        //val visitedNodes = mutableSetOf<PathNode<T>>()
        val astarCost =  mutableMapOf<T,PathNode<T>>(). withDefault { PathNode(null, Int.MAX_VALUE, Int.MAX_VALUE) }

        var iterations = 0
        // while the priority Q has elements, get the top one (least cost as per Comparator)
        while (toVisitQueue.isNotEmpty()) {
            val currentNode = toVisitQueue.poll()
            // if this is the endNode ID, we are done
            if (currentNode.node?.getId()!! == endState.getId()) {
                val minCostPath = MinCostPath<T>()
                minCostPath.minCost = currentNode.costFromStart
                minCostPath.numberOfIterations = iterations
                minCostPath.path = getMinCostPath(currentNode.node.getId(), startState.getId(), astarCost)
                return minCostPath
            }
            // else for each connected node
            currentNode.node.getConnectedNodes().forEach { connectedNode ->
                ++iterations
                val nextPathNode = PathNode(connectedNode.node, connectedNode.costFromPrev, Int.MAX_VALUE)
                //if (visitedNodes.contains(nextPathNode))
                  //  return@forEach
                //visitedNodes.add(nextPathNode)
                // calculate the new cost to that node and the new *estimated* total cost to the end node
                val newCost = currentNode.costFromStart + connectedNode.costFromPrev
                val newTotalCost = newCost + connectedNode.node.heuristic()
                // if the new cost is less that what we have already recorded in the map of nodes/costs
                // update the map with the new costs and "updatedBy" (to be able to back-track the min.cost path)
                if (newCost < astarCost.getValue(connectedNode.node.getId()).costFromStart) {
                    nextPathNode.updatedBy = currentNode.node.getId()
                    nextPathNode.costFromStart = newCost
                    nextPathNode.estTotCostToEnd = newTotalCost
                    astarCost[connectedNode.node.getId()] = nextPathNode
                    // and put the updated new node back into the priority queue
                    toVisitQueue.add(nextPathNode)
                }
            }
        }
        throw AStarException("no path found from ${startState.getId()} to ${endState.getId()}")
    }

    fun getMinCostPath(minCostKey: T, startKey: T, astarCost: Map<T, PathNode<T>>): List<T> {
        var key = minCostKey
        val path = mutableListOf<T>()
        do {
            path.add(key)
            key = astarCost.getValue(key).updatedBy ?: startKey
        } while (key != startKey)
        path.add(key)
        return path.reversed()
    }
}