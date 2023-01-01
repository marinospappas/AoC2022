package mpdev.aoc2022.day24

import mpdev.aoc2022.common.*
import mpdev.aoc2022.day24.InputDay24.Companion.end
import mpdev.aoc2022.day24.InputDay24.Companion.overlayGrid
import mpdev.aoc2022.day24.InputDay24.Companion.start
import mpdev.aoc2022.utils.Dijkstra
import mpdev.aoc2022.utils.Graph
import java.awt.Point

class SolutionProcessorDay24: SolutionProcessor<InputDay24> {

    /** part 1 calculation */
    override fun part1(input: InputDay24): String {
        val minPath = Dijkstra<NodeId>()
        val res = minPath.runIt(input.graphData[input.startId], input.graphData[input.endId])
        println("number of iterations: ${res.numberOfIterations}")
        println(res.path)
        return (res.path[res.path.lastIndex-1].blizIndx + 1).toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay24): String {
        println("start = ${input.startId} end = ${input.endId}")
        input.overlay(0)
        println(overlayGrid.gridToString())
        println("****")

        val minPath = Dijkstra<NodeId>()
        var res = minPath.runIt(input.graphData[input.startId], input.graphData[input.endId])
        println("number of iterations 1: ${res.numberOfIterations}")
        val path1 = res.path[res.path.lastIndex - 1].blizIndx + 1
        println(res.path)
        input.overlay(path1)
        println(overlayGrid.gridToString())
        println("path1 $path1")

        input.startId = NodeId(Point(end.x, end.y), path1)
        input.endId = NodeId(Point(start.x, start.y), Int.MIN_VALUE)
        input.graphData = Graph<NodeId> { id -> input.getConnectedNodes(id) }
        input.graphData.addNode(input.startId)
        input.graphData.addNode(input.endId)
        println("start = ${input.startId} end = ${input.endId}")
        res = minPath.runIt(input.graphData[input.startId], input.graphData[input.endId])
        println("number of iterations 1: ${res.numberOfIterations}")
        val path2 = res.path[res.path.lastIndex - 1].blizIndx + 1 - res.path[0].blizIndx
        println(res.path)
        input.overlay(path1 + path2)
        println(overlayGrid.gridToString())
        println("path2 $path2")

        input.startId = NodeId(Point(start.x, start.y), path1 + path2)
        input.endId = NodeId(Point(end.x, end.y), Int.MIN_VALUE)
        input.graphData = Graph<NodeId> { id -> input.getConnectedNodes(id) }
        input.graphData.addNode(input.startId)
        input.graphData.addNode(input.endId)
        println("start = ${input.startId} end = ${input.endId}")
        res = minPath.runIt(input.graphData[input.startId], input.graphData[input.endId])
        println("number of iterations 1: ${res.numberOfIterations}")
        val path3 = res.path[res.path.lastIndex - 1].blizIndx + 1 - res.path[0].blizIndx
        println("path3 $path3")
        println(res.path)
        input.overlay(path1 + path2 + path3)
        println(overlayGrid.gridToString())

        return (path1 + path2 + path3).toString()
    }

}