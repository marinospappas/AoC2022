package mpdev.aoc2022.day16

import mpdev.aoc2022.common.*
import mpdev.aoc2022.utils.Dijkstra
import mpdev.aoc2022.utils.combinations
import mpdev.aoc2022.utils.permutations

class SolutionProcessorDay16: SolutionProcessor<InputDay16> {

    /** part 1 calculation */
    override fun part1(input: InputDay16): String {
        val destinations = input.valveMap.values.filter { it.rate > 0 }.mapTo(arrayListOf()) { it.id }
        println("number of destinations: ${destinations.size}")
        println("all destinations: $destinations")
        val results = mutableListOf<Pair<Int,Int>>()
        destinations.permutations(destinations.size).forEach {
        //  val it = listOf("DD","BB","JJ","HH","EE","CC")
            calculate1(it, input, results)
        }
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
        println(results.sortedBy { it.first }.reversed())
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")

        return ""
    }


    override fun part2(input: InputDay16): String {
        return ""
    }

    fun calculate1(destinations: List<String>, input:InputDay16, results: MutableList<Pair<Int,Int>>) {
        //println(destinations)
        var start = input.startId
        val minPath = Dijkstra(input.graph.costs)
        var result = 0
        var time = 0
        input.clearValvesState()
        destinations.forEach { dest ->
            //println("$start to ${dest}")
            try {
                val res = minPath.runIt(input.graph[start], input.graph[Pair(dest,1)])
                //println("number of iterations: ${res.numberOfIterations}")
                //println("path ${res.path}")
                //println("flow: ${input.calculateFlowAndTime(res.path)}")
                result += input.calculateFlowAndTime(res.path).first
                time += input.calculateFlowAndTime(res.path).second
                //println("overall: $result, $time")
                //println("********")
            } catch(e: Dijkstra.DijkstraException) {println(e.message)}
            start = Pair(dest,1)
        }
        //println(">>>>>>> total : $result, $time")
        results.add(Pair(result,time))
    }
}