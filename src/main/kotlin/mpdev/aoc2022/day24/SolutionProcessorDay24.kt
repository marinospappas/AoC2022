package mpdev.aoc2022.day24

import mpdev.aoc2022.common.*
import mpdev.aoc2022.day24.Day24.Companion.end
import mpdev.aoc2022.day24.Day24.Companion.start
import mpdev.aoc2022.day24.Day24.NodeId

class SolutionProcessorDay24: SolutionProcessor<Day24> {

    /** part 1 calculation */
    override fun part1(puzzle: Day24): String {
        val res = puzzle.findPath(NodeId(start,0), NodeId(end,-1))
        println("number of iterations: ${puzzle.iterCount}")
        return res.toString()
    }

    /** part 2 calculation */
    override fun part2(puzzle: Day24): String {
        val res1 = puzzle.findPath(NodeId(start,0), NodeId(end,-1))
        println("res1 $res1, number of iterations: ${puzzle.iterCount}")
        val res2 = puzzle.findPath(NodeId(end, res1), NodeId(start,-1))
        println("res2 $res2, number of iterations: ${puzzle.iterCount}")
        val res3 = puzzle.findPath(NodeId(start, res2), NodeId(end, -1))
        println("res3 $res3, number of iterations: ${puzzle.iterCount}")
        return res3.toString()
    }

}