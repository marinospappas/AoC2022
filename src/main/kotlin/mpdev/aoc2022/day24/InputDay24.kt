package mpdev.aoc2022.day24

import mpdev.aoc2022.utils.Graph
import mpdev.aoc2022.utils.GraphNode
import java.awt.Point
import java.lang.StringBuilder

class InputDay24(var inputList: List<String>) {

    private val START = Pair(500,0)

    companion object {
        lateinit var grid: Array<CharArray>
        lateinit var dimensions: Point
        lateinit var overlayGrid: Array<CharArray>
        lateinit var start: Point
        lateinit var end: Point
    }
    val blizardList = mutableListOf<Blizard>()
    val blizardStates = mutableListOf<MutableList<Blizard>>()

    var graphData = Graph<NodeId> { id -> getConnectedNodes(id) }
    var startId: NodeId
    var endId: NodeId

    init {
        dimensions = Point(inputList.first().length, inputList.size)
        grid =  Array(dimensions.y) { inputList[it].toCharArray() }
        grid.indices.forEach { y -> grid[y].indices.forEach { x ->   // keep blizzards in a separate list
                if ("^>v<".contains(grid[y][x])) {
                    blizardList.add(Blizard(Point(x,y), grid[y][x]))
                    grid[y][x] = '.'
                }
            }
        }
        start = Point(grid.first().indexOfFirst { it == '.' }, 0)
        end = Point(grid.last().indexOfFirst { it == '.' }, dimensions.y-1)
        processBlizards(10000)
        // add start and end nodes to the graph
        startId = NodeId(start, 0)
        endId = NodeId(end, Int.MIN_VALUE)
        graphData.addNode(startId)
        graphData.addNode(endId)
    }

    // connected nodes are calculated dynamically
    fun getConnectedNodes(nodeId: NodeId): List<GraphNode<NodeId>> {
        val nodes = mutableListOf<GraphNode<NodeId>>()
        overlay(nodeId.blizIndx+1)
        val listOfCoords = mutableListOf(Point(nodeId.pos.x-1,nodeId.pos.y), Point(nodeId.pos.x+1, nodeId.pos.y))
        if (nodeId.pos.y > 0)
            listOfCoords.add(Point(nodeId.pos.x, nodeId.pos.y-1))
        if (nodeId.pos.y < dimensions.y-1)
            listOfCoords.add(Point(nodeId.pos.x, nodeId.pos.y+1))
        listOfCoords.forEach {
            if (it != startId.pos && overlayGrid[it.y][it.x] == '.')
                if (Point(it.x, it.y) == endId.pos)
                    nodes.add(GraphNode(NodeId(Point(it.x, it.y), Int.MIN_VALUE)) { id -> getConnectedNodes(id) })
                else
                    nodes.add(GraphNode(NodeId(Point(it.x, it.y), nodeId.blizIndx + 1)) { id -> getConnectedNodes(id) })
        }
        // add the node itself as its own neighbour (= don't move)
        if (nodes.isEmpty())
            nodes.add(GraphNode(NodeId(nodeId.pos, nodeId.blizIndx+1)) { id -> getConnectedNodes(id) } )
        return nodes
    }

    fun overlay(blizIndx: Int) {
        overlayGrid = Array(dimensions.y) { y-> CharArray(dimensions.x) { x-> grid[y][x] } }
        blizardStates[blizIndx].forEach {
            overlayGrid[it.pos.y][it.pos.x] = if (overlayGrid[it.pos.y][it.pos.x] == '.') it.direction else '2'
        }
    }

    private fun processBlizards(repeat: Int) {
        blizardStates.add(blizardList)
        (1 until repeat).forEach {
            val newState = mutableListOf<Blizard>()
            blizardStates.last().forEach {
                newState.add(Blizard(Point(it.pos.x,it.pos.y), it.direction))
                newState.last().moveToNewPos()
            }
            blizardStates.add(newState)
        }
    }

    class Blizard(var pos: Point, val direction: Char) {
        fun moveToNewPos() {
            when (direction) {
                '>' -> pos = Point(if (pos.x+1 == dimensions.x-1) 1 else pos.x + 1, pos.y)
                'v' -> pos = Point(pos.x, if (pos.y+1 == dimensions.y-1) 1 else pos.y + 1 )
                '<' -> pos = Point(if (pos.x-1 == 0) dimensions.x - 2 else pos.x - 1, pos.y)
                '^' -> pos = Point(pos.x, if (pos.y-1 == 0) dimensions.y - 2 else pos.y - 1 )
            }
        }
        override fun toString() = "${pos.x}, ${pos.y} $direction"
    }
}

class NodeId(val pos: Point, val blizIndx: Int) {
    override fun equals(other: Any?): Boolean {
        return other is NodeId && this.pos.x == other.pos.x && this.pos.y == other.pos.y && this.blizIndx == other.blizIndx
    }
    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + pos.hashCode()
        hash = hash * 31 + blizIndx
        return hash
    }
    override fun toString() = "${pos.x},${pos.y} $blizIndx"
}


fun Array<CharArray>.gridToString(): String {
    val s = StringBuilder()
    for (i in this.indices) {
        this[i].toList().forEach { s.append(it) }
        s.append("\n")
    }
    return s.toString()
}