package mpdev.aoc2022.day16

import mpdev.aoc2022.utils.Dijkstra
import mpdev.aoc2022.utils.Graph
import mpdev.aoc2022.utils.GraphNode
import mpdev.aoc2022.utils.combinations
import java.util.*

const val VALVE_STATE_OFF = 0
const val VALVE_STATE_ON = 1

class Day16(var valveMap: Map<String,Valve>, val connections: List<Pair<String,String>>) {

    val graph = Graph<String>()
    val minPaths = mutableMapOf<Pair<String,String>, Int>()
    var startId = "AA"
    val numOfFunctioningValves = valveMap.values.count { it.rate > 0 }

    init {
        valveMap.forEach { (k,_) ->
            graph.addNode(k)
        }
        connections.forEach { graph.connectBothWays(it.first, it.second) }
    }

    /** find minimum path for all combinations of working valves (and the start) */
    fun findMinPathCombinations() {
        val algo = Dijkstra<String>()
        (listOf(valveMap[startId]) + valveMap.values.filter { it.rate > 0 }).combinations(2).forEach {
            val start = it[0]!!.id
            val end = it[1]!!.id
            val res = algo.runIt(graph[start], graph[end])
            minPaths[Pair(start, end)] = res.minCost + 1     // +1 that is required to open the valve
        }
    }

    fun findMaxPressureRelief(maxTime: Int) {
        var maxPressureRelieved = 0
        val queue: Queue<State> = LinkedList()
        queue.add(State(startId))
        val seenBefore = mutableSetOf<State>()
        while (queue.isNotEmpty()) {
            val curState = queue.poll()
            // if all valves open or max time reached just calculate result
            if (curState.openedValves.size == numOfFunctioningValves || curState.elapsedTime >= maxTime) {
                //...
                continue
            }
            // find remaining unopened valves
            val unopened = valveMap.keys.toMutableList()
            unopened.removeAll(curState.openedValves)
            unopened.forEach { dest ->
                val cost = minPaths[Pair(curState.curValve, dest)]!!
                val newElapsed = curState.elapsedTime + cost
                if (newElapsed >= maxTime) {
                    //...
                }
                else {

                }

            }
        }
    }

    fun getCurrentRate() = valveMap.values.filter { it.state == VALVE_STATE_ON }.sumOf { it.rate }

    fun clearValvesState() {
        valveMap.forEach { (_,v) -> v.state = VALVE_STATE_OFF }
    }

    fun calculateFlowAndTime(path: List<String>): Pair<Int,Int> {
        var totalFlow = 0
        var time = 0
        for (i in 0 until path.lastIndex) {
            valveMap[path[i]]!!.state = VALVE_STATE_ON
            totalFlow += getCurrentRate() * 1 // rate per minute
            ++time
        }
        return Pair(totalFlow,time)
    }


}

data class Valve(val id: String, val rate: Int, var state: Int = VALVE_STATE_OFF)

data class State(val curValve: String, val openedValves: List<String> = listOf(),
                 val elapsedTime: Int = 0, val pressureRelieved: Int = 0)
