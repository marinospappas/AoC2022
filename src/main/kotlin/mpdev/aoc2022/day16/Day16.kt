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
    private val numOfFunctioningValves = valveMap.values.count { it.rate > 0 }

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

    fun findMaxPressureRelief1(maxTime: Int): Int {
        var maxPressureRelieved = 0
        val queue: Queue<State> = LinkedList()
        queue.add(State(startId))
        val seenBefore = mutableSetOf<State>()
        while (queue.isNotEmpty()) {
            val curState = queue.poll()
            // if all valves open or max time reached just calculate result
            if (curState.openedValves.size == numOfFunctioningValves || curState.elapsedTime >= maxTime) {
                val relievedAtEnd = getRelievedPressureAtEnd(curState, maxTime)
                maxPressureRelieved = maxOf(maxPressureRelieved, relievedAtEnd)
                continue
            }
            // find remaining unopened valves
            val unopened = valveMap.values.filter { it.rate > 0 }.map{ it.id }.toMutableList()
            unopened.removeAll(curState.openedValves)
            unopened.remove(startId)
            unopened.forEach { dest ->
                val cost = minPaths[Pair(curState.curValve, dest)] ?: minPaths[Pair(dest, curState.curValve)]!!
                val newElapsed = curState.elapsedTime + cost
                if (newElapsed >= maxTime) {
                    val relievedAtEnd = getRelievedPressureAtEnd(curState, maxTime)
                    maxPressureRelieved = maxOf(maxPressureRelieved, relievedAtEnd)
                }
                else {
                    val totalRate = valveMap.values.filter { curState.openedValves.contains(it.id) }.sumOf { it.rate }
                    val thisRelieved = totalRate * cost
                    val nextState = State(dest, curState.openedValves+dest,
                        curState.elapsedTime+cost, curState.pressureRelieved+thisRelieved)
                    if (!seenBefore.contains(nextState)) {
                        queue.add(nextState)
                        seenBefore.add(nextState)
                    }
                }
            }
        }
        return maxPressureRelieved
    }

    fun getRelievedPressureAtEnd(state: State, maxtime: Int): Int {
        val remainingTime = maxtime - state.elapsedTime
        val totalRate = valveMap.values.filter { state.openedValves.contains(it.id) }.sumOf { it.rate }
        return state.pressureRelieved + totalRate * remainingTime
    }
}

data class Valve(val id: String, val rate: Int)

data class State(val curValve: String, val openedValves: List<String> = listOf(),
                 val elapsedTime: Int = 0, val pressureRelieved: Int = 0)
