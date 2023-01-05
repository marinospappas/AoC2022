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
        val res = puzzle.findPath(puzzle.startId, puzzle.endId)
        println("number of iterations: ${puzzle.iterCount}")
        return res.toString()
    }

    /** part 2 calculation */
    override fun part2(puzzle: Day24): String {
        println("start = ${puzzle.startId} end = ${puzzle.endId}")
        val res1 = puzzle.findPath(puzzle.startId, puzzle.endId)
        println("number of iterations: ${puzzle.iterCount}")
        println("res1 $res1")
        val start1 = NodeId(puzzle.endId.pos, res1)
        val end1 = NodeId(puzzle.startId.pos, Int.MIN_VALUE)
        val res2 = puzzle.findPath(start1, end1)
        println("number of iterations: ${puzzle.iterCount}")
        println("res2 $res2")
        val start2 = NodeId(puzzle.startId.pos, res2)
        val end2 = NodeId(puzzle.endId.pos, Int.MIN_VALUE)
        val res3 = puzzle.findPath(start2, end2)
        println("number of iterations: ${puzzle.iterCount}")
        println("res3 $res3")
        return res3.toString()
    }

}