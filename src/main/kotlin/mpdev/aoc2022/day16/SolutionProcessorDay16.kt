package mpdev.aoc2022.day16

import mpdev.aoc2022.common.*
import mpdev.aoc2022.utils.Dijkstra
import mpdev.aoc2022.utils.permutations

class SolutionProcessorDay16: SolutionProcessor<Day16> {

    /** part 1 calculation */
    override fun part1(input: Day16): String {
        input.findMinPathCombinations()
        return ""
    }


    override fun part2(input: Day16): String {
        return ""
    }

    fun calculate1(destinations: List<String>, input:Day16, results: MutableList<Pair<Int,Int>>) {
        //println(destinations)
        var start = input.startId
        val minPath = Dijkstra(input.graph.costs)
        var result = 0
        var time = 0
        input.clearValvesState()
        destinations.forEach { dest ->
            //println("$start to ${dest}")
            try {
                val res = minPath.runIt(input.graph[start], input.graph[dest])
                //println("number of iterations: ${res.numberOfIterations}")
                //println("path ${res.path}")
                //println("flow: ${input.calculateFlowAndTime(res.path)}")
                result += input.calculateFlowAndTime(res.path).first
                time += input.calculateFlowAndTime(res.path).second
                //println("overall: $result, $time")
                //println("********")
            } catch(e: Dijkstra.DijkstraException) {println(e.message)}
            start = dest
        }
        //println(">>>>>>> total : $result, $time")
        results.add(Pair(result,time))
    }
}