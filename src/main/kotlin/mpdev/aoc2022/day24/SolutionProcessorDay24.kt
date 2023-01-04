package mpdev.aoc2022.day24

import mpdev.aoc2022.common.*
import mpdev.aoc2022.day24.Day24.Companion.end
import mpdev.aoc2022.day24.Day24.Companion.overlayGrid
import mpdev.aoc2022.day24.Day24.Companion.start
import mpdev.aoc2022.utils.Dijkstra
import mpdev.aoc2022.utils.Graph
import java.awt.Point

class SolutionProcessorDay24: SolutionProcessor<Day24> {

    /** part 1 calculation */
    override fun part1(puzzle: Day24): String {
        val minPath = Dijkstra<NodeId>()
        val res = minPath.runIt(puzzle.graphData[puzzle.startId], puzzle.graphData[puzzle.endId])
        println("number of iterations: ${res.numberOfIterations}")
        println(res.path)
        return (res.path[res.path.lastIndex-1].blizIndx + 1).toString()
    }

    /** part 2 calculation */
    override fun part2(puzzle: Day24): String {
        println("start = ${puzzle.startId} end = ${puzzle.endId}")
        puzzle.overlay(0)
        println(overlayGrid.gridToString())
        println("****")

        val minPath = Dijkstra<NodeId>()
        var res = minPath.runIt(puzzle.graphData[puzzle.startId], puzzle.graphData[puzzle.endId])
        println("number of iterations 1: ${res.numberOfIterations}")
        val path1 = res.path[res.path.lastIndex - 1].blizIndx + 1
        println(res.path)
        puzzle.overlay(path1)
        println(overlayGrid.gridToString())
        println("path1 $path1")

        puzzle.startId = NodeId(Point(end.x, end.y), path1)
        puzzle.endId = NodeId(Point(start.x, start.y), Int.MIN_VALUE)
        puzzle.graphData = Graph<NodeId> { id -> puzzle.getConnectedNodes(id) }
        puzzle.graphData.addNode(puzzle.startId)
        puzzle.graphData.addNode(puzzle.endId)
        println("start = ${puzzle.startId} end = ${puzzle.endId}")
        res = minPath.runIt(puzzle.graphData[puzzle.startId], puzzle.graphData[puzzle.endId])
        println("number of iterations 1: ${res.numberOfIterations}")
        val path2 = res.path[res.path.lastIndex - 1].blizIndx + 1 - res.path[0].blizIndx
        println(res.path)
        puzzle.overlay(path1 + path2)
        println(overlayGrid.gridToString())
        println("path2 $path2")

        puzzle.startId = NodeId(Point(start.x, start.y), path1 + path2)
        puzzle.endId = NodeId(Point(end.x, end.y), Int.MIN_VALUE)
        puzzle.graphData = Graph<NodeId> { id -> puzzle.getConnectedNodes(id) }
        puzzle.graphData.addNode(puzzle.startId)
        puzzle.graphData.addNode(puzzle.endId)
        println("start = ${puzzle.startId} end = ${puzzle.endId}")
        res = minPath.runIt(puzzle.graphData[puzzle.startId], puzzle.graphData[puzzle.endId])
        println("number of iterations 1: ${res.numberOfIterations}")
        val path3 = res.path[res.path.lastIndex - 1].blizIndx + 1 - res.path[0].blizIndx
        println("path3 $path3")
        println(res.path)
        puzzle.overlay(path1 + path2 + path3)
        println(overlayGrid.gridToString())

        return (path1 + path2 + path3).toString()
    }

}