package mpdev.aoc2022.day12

import mpdev.aoc2022.utils.Graph
import kotlin.math.abs

class InputDay12(var heightList: List<String>) {

    val maxX = heightList.first().length - 1
    val maxY = heightList.size - 1

    var graphData = Graph<Pair<Int,Int>>()
    var startId = getPoint('S')
    var endId = getPoint('E')

    fun getPoint(c: Char): Pair<Int,Int> {
        (0..maxX).forEach { i -> (0..maxY).forEach { j -> if (heightList[j][i] == c) return Pair(i,j) } }
        return Pair(0,0)
    }

    fun getPoints(s: String): List<Pair<Int,Int>> {
        var pointsList = mutableListOf<Pair<Int,Int>>()
        (0..maxX).forEach { i -> (0..maxY).forEach { j -> if (s.contains(heightList[j][i])) pointsList.add(Pair(i,j)) } }
        return pointsList
    }

    fun createGraph(direction: Char): Graph<Pair<Int, Int>> {
        val graph = Graph<Pair<Int,Int>>()
        // build graph
        for (i in 0..maxX)
            for (j in 0..maxY)
                graph.addNode(Pair(i,j))
        // update neighbours
        for (i in 0..maxX)
            for (j in 0..maxY) {
                val thisId = Pair(i,j)
                val neighbours = if (direction == 'U')
                    getNeighbourIdsUp(thisId)
                else
                    getNeighbourIdsDown(thisId)
                neighbours.forEach { neighbour ->
                    graph.connect(thisId, neighbour)
                    graph.updateCost(thisId, neighbour, 1)
                }
                graph.updateHeuristic(thisId, calculateHeuristcUp(thisId))
            }
        return graph
    }

    private fun getNeighbourIdsUp(thisId: Pair<Int,Int>): List<Pair<Int,Int>> {
        val ids = mutableListOf<Pair<Int,Int>>()
        val i = thisId.first
        val j = thisId.second
        for (neighbour in setOf(Pair(i,j-1),Pair(i-1,j),Pair(i+1,j),Pair(i,j+1)))
            if (neighbour.first >= 0 && neighbour.second >= 0 && neighbour.first <= maxX && neighbour.second <= maxY) {
                when {
                    thisId == startId -> ids.add(neighbour)
                    neighbour != endId && heightList[neighbour.second][neighbour.first] <= heightList[thisId.second][thisId.first] ->
                        ids.add(neighbour)
                    neighbour != endId && heightList[neighbour.second][neighbour.first] == heightList[thisId.second][thisId.first]+1 ->
                        ids.add(neighbour)
                    neighbour == endId && heightList[thisId.second][thisId.first] == 'z' -> ids.add(neighbour)
                }
            }
        return ids
    }

    private fun getNeighbourIdsDown(thisId: Pair<Int,Int>): List<Pair<Int,Int>> {
        val ids = mutableListOf<Pair<Int,Int>>()
        val i = thisId.first
        val j = thisId.second
        for (neighbour in setOf(Pair(i,j-1),Pair(i-1,j),Pair(i+1,j),Pair(i,j+1)))
            if (neighbour.first >= 0 && neighbour.second >= 0 && neighbour.first <= maxX && neighbour.second <= maxY) {
                when {
                    thisId == startId -> ids.add(neighbour)
                    neighbour != endId && heightList[neighbour.second][neighbour.first] <= heightList[thisId.second][thisId.first] ->
                        ids.add(neighbour)
                    neighbour != endId && heightList[neighbour.second][neighbour.first] == heightList[thisId.second][thisId.first]+1 ->
                        ids.add(neighbour)
                    neighbour == endId && heightList[thisId.second][thisId.first] == 'a' -> ids.add(neighbour)
                }
            }
        return ids
    }

    fun verifyNeighbours() {
        for (i in 0..maxX)
            for (j in 0..maxY) {
                graphData[Pair(i, j)].neighbours.forEach {
                    if (Pair(i,j) != startId
                        && heightList[it.getId().second][it.getId().first] - heightList[j][i] > 1)
                        println("invalid neighbour from ${Pair(i,j)} '${heightList[j][i]}' " +
                        "to ${it.getId()} '${heightList[it.getId().second][it.getId().first]}'")
                }
            }
    }

    fun calculateHeuristcUp(nodeId: Pair<Int,Int>): Int {
        return  if (nodeId == endId) 0
                else
                    abs(nodeId.first - endId.first) +
                    abs(nodeId.second - endId.second) +
                    abs(heightList[nodeId.second][nodeId.first] - 'z')
    }
}
