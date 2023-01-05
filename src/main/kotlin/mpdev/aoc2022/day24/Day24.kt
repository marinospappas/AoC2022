package mpdev.aoc2022.day24

import mpdev.aoc2022.common.RunTimeException
import mpdev.aoc2022.utils.Graph
import mpdev.aoc2022.utils.GraphNode
import mpdev.aoc2022.utils.plus
import java.awt.Point
import java.lang.StringBuilder
import java.util.*
import kotlin.NoSuchElementException
import kotlin.math.abs

class Day24(var inputList: List<String>) {

    companion object {
        lateinit var grid: Array<CharArray>
        lateinit var dimensions: Point
        lateinit var overlayGrid: Array<CharArray>
        lateinit var start: Point
        lateinit var end: Point
    }
    val blizzardList = mutableListOf<Blizzard>()
    private val blizzardStates = mutableListOf<List<Blizzard>>()
    var startId: NodeId
    var endId: NodeId
    var iterCount = 0

    init {
        dimensions = Point(inputList.first().length, inputList.size)
        grid =  Array(dimensions.y) { inputList[it].toCharArray() }
        grid.indices.forEach { y -> grid[y].indices.forEach { x ->   // keep blizzards in a separate list
                if ("^>v<".contains(grid[y][x])) {
                    blizzardList.add(Blizzard(Point(x,y), grid[y][x]))
                    grid[y][x] = '.'
                }
            }
        }
        start = Point(grid.first().indexOfFirst { it == '.' }, 0)
        end = Point(grid.last().indexOfFirst { it == '.' }, dimensions.y-1)
        // preprocess blizzarda for the next n minutes
        processBlizzards(1000)
        startId = NodeId(start, 0)
        endId = NodeId(end, Int.MIN_VALUE)
    }

    fun findPath(start: NodeId, end: NodeId): Int {
        iterCount = 0
        val queue = PriorityQueue(compareBy(NodeId::blizIndx))
        val seen = mutableSetOf(start)
        queue.add(start)
        while (!queue.isEmpty()) {
            ++iterCount
            val current = queue.poll()
            //if (current.pos == end.pos)
            //    return current.blizIndx
            getConnectedNodes(current, end).forEach {
                if (it.pos == end.pos)
                    return current.blizIndx+1
                if (seen.add(it))
                    queue.add(it)
            }
        }
        throw RunTimeException("could not find path from $start to $end")
    }

    // connected nodes are calculated dynamically depending on the state of the blizzards
    private fun getConnectedNodes(nodeId: NodeId, endId: NodeId): List<NodeId> {
        val nodes = mutableListOf<NodeId>()
        listOf(Point(-1,0), Point(+1,0), Point(0,-1), Point(0,+1)).forEach { point ->
            val neighbour = nodeId.pos + point
            if (neighbour == endId.pos)
                return listOf(endId)
            if (neighbour == startId.pos
                || (neighbour.x > 0 && neighbour.x < grid[0].lastIndex
                        && neighbour.y > 0 && neighbour.y < grid.lastIndex
                        && blizzardStates[nodeId.blizIndx+1].none { it.pos == neighbour }))
                nodes.add(NodeId(neighbour, nodeId.blizIndx+1))
        }
        // add the node itself as its own neighbour (= don't move)
        if (nodes.isEmpty())
            nodes.add(NodeId(nodeId.pos, nodeId.blizIndx+1))
        print("neighbours for nodeId (${nodeId.pos.x},${nodeId.pos.y}) time ${nodeId.blizIndx} are ")
        nodes.forEach { print("(${it.pos.x},${it.pos.y}) ") }
        println()
        return nodes
    }

    fun overlay(blizIndx: Int) {
        overlayGrid = Array(dimensions.y) { y-> CharArray(dimensions.x) { x-> grid[y][x] } }
        blizzardStates[blizIndx].forEach {
            overlayGrid[it.pos.y][it.pos.x] = if (overlayGrid[it.pos.y][it.pos.x] == '.') it.direction else '2'
        }
    }

    private fun processBlizzards(repeat: Int) {
        blizzardStates.add(blizzardList)
        (1 until repeat).forEach { _ ->
            val newState = mutableListOf<Blizzard>()
            blizzardStates.last().forEach {
                newState.add(Blizzard(Point(it.pos.x,it.pos.y), it.direction))
                newState.last().moveToNewPos()
            }
            blizzardStates.add(newState)
        }
    }

    data class Blizzard(var pos: Point, val direction: Char) {
        fun moveToNewPos() {
            when (direction) {
                '>' -> pos = Point(if (pos.x+1 == dimensions.x-1) 1 else pos.x + 1, pos.y)
                'v' -> pos = Point(pos.x, if (pos.y+1 == dimensions.y-1) 1 else pos.y + 1 )
                '<' -> pos = Point(if (pos.x-1 == 0) dimensions.x - 2 else pos.x - 1, pos.y)
                '^' -> pos = Point(pos.x, if (pos.y-1 == 0) dimensions.y - 2 else pos.y - 1 )
            }
        }
    }
}

data class NodeId(val pos: Point, val blizIndx: Int)

fun Array<CharArray>.gridToString(): String {
    val s = StringBuilder()
    for (i in this.indices) {
        this[i].toList().forEach { s.append(it) }
        s.append("\n")
    }
    return s.toString()
}