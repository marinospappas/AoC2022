package mpdev.aoc2022.day12

import mpdev.aoc2022.common.*
import mpdev.aoc2022.utils.AStar
import mpdev.aoc2022.utils.Dijkstra
import mpdev.aoc2022.utils.ConsoleColour

class SolutionProcessorDay12: SolutionProcessor<InputDay12> {

    /** part 1 calculation */
    override fun part1(input: InputDay12): String {
        input.graphData = input.createGraph('U')
        input.verifyNeighbours()
        val minPath = Dijkstra<Pair<Int,Int>>(input.graphData.costs)
        //val minPath = AStar<Pair<Int,Int>>(input.graphData.costs, input.graphData.heuristics)
        val res = minPath.runIt(input.graphData[input.startId], input.graphData[input.endId])
        for (i in 0..input.maxY) {
            for (j in 0..input.maxX)
                if (res.path.contains(Pair(j,i)))
                    print(ConsoleColour.RED + input.heightList[i][j].uppercaseChar() + ConsoleColour.RESET)
                else
                    print(input.heightList[i][j])
            println()
        }
        println(res.path)
        return (res.path.size - 1).toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay12): String {
        input.graphData = input.createGraph('U')
        val minPath = Dijkstra<Pair<Int,Int>>(input.graphData.costs)
        val statingPoints = input.getPoints("aS")
        val resultsList = mutableListOf<Int>()
        statingPoints.forEach {
            try {
                resultsList.add(minPath.runIt(input.graphData[it], input.graphData[input.endId]).path.size - 1)
            }
            catch (e: Dijkstra.DijkstraException) {
                println (e.message)
            }
        }
        println(resultsList)
        return resultsList.min().toString()
    }

    fun part1_1(input: InputDay12): String {        // go down the hill as well
        input.startId = input.getPoint('E')
        input.endId = input.getPoint('S')
        input.graphData = input.createGraph('D')
        val minPath = AStar<Pair<Int,Int>>(input.graphData.costs, input.graphData.heuristics)
        val res = minPath.runIt(input.graphData[input.startId], input.graphData[input.endId])
        for (i in 0..input.maxY) {
            for (j in 0..input.maxX)
                if (res.path.contains(Pair(j,i)))
                    print(input.heightList[i][j])
                else
                    print(".")
            println()
        }
        println(res.path.size - 1)
        return (res.path.size - 1).toString()
    }
}