package mpdev.aoc2022.day16

import mpdev.aoc2022.utils.Dijkstra
import mpdev.aoc2022.utils.Graph
import mpdev.aoc2022.utils.combinations
import java.util.*

class Day16(var valveMap: Map<String,Valve>, connections: List<Pair<String,String>>) {

    val graph = Graph<String>()
    val minPaths = mutableMapOf<Pair<String,String>, Int>()
    private var startId = "AA"
    private val functioningValves = valveMap.values.filter { it.rate > 0 }.map{ it.id }
    private val numOfFunctioningValves = valveMap.values.count { it.rate > 0 }
    private val relievedPressureForValveCombiMap = mutableMapOf<List<String>,Int>()

    init {
        valveMap.forEach { (k,_) ->
            graph.addNode(k)
        }
        connections.forEach { graph.connectBothWays(it.first, it.second) }
    }

    /** find minimum path for all combinations of working valves (and the start) */
    fun calculateMinPathCombinations() {
        val algo = Dijkstra<String>()
        (listOf(valveMap[startId]) + valveMap.values.filter { it.rate > 0 }).combinations(2).forEach {
            val start = it[0]!!.id
            val end = it[1]!!.id
            val res = algo.runIt(graph[start], graph[end])
            minPaths[Pair(start, end)] = res.minCost + 1     // +1 that is required to open the valve
        }
    }

    /**
     * calculate max pressure relief possible (part 1)
     * and save pressure relief for each opened valve combination (part 2)
     */
    fun calculateMaxPressureRelief1(maxTime: Int): Int {
        var count = 0
        var maxPressureRelieved = 0
        val queue: Queue<State> = LinkedList()
        queue.add(State(startId))
        while (queue.isNotEmpty()) {
            val curState = queue.poll()
            ++count
            val relievedAtEnd = getRelievedPressureAtEnd(curState, maxTime)

            // save the amount that will be relieved at the end for this opened valve combination
            // the valve combination is sorted as only the valve ids matter and not the sequence
            // in the end only the maximum value for a specific set of valves is retained in the map
            val key = curState.openedValves.sorted()
            if (relievedPressureForValveCombiMap[key] == null
                || relievedPressureForValveCombiMap[key]!! < relievedAtEnd)
               relievedPressureForValveCombiMap[key] = relievedAtEnd

            // if all valves open or max time reached just calculate result
            if (curState.openedValves.size == numOfFunctioningValves || curState.elapsedTime >= maxTime) {
                maxPressureRelieved = maxOf(maxPressureRelieved, relievedAtEnd)
                continue
            }

            // find remaining unopened valves and try all next states (for each remaining unopened valve)
            val unopened = functioningValves - curState.openedValves.toSet()
            unopened.forEach { dest ->
                val cost = minPaths[Pair(curState.curValve, dest)] ?: minPaths[Pair(dest, curState.curValve)]!!
                val newElapsed = curState.elapsedTime + cost
                if (newElapsed >= maxTime) {
                    maxPressureRelieved = maxOf(maxPressureRelieved, relievedAtEnd)
                }
                else {
                    val totalRate = valveMap.values.filter { curState.openedValves.contains(it.id) }.sumOf { it.rate }
                    val thisRelieved = totalRate * cost
                    val nextState = State(dest, curState.openedValves+dest,
                        curState.elapsedTime+cost, curState.pressureRelieved+thisRelieved)
                    queue.add(nextState)
                }
            }
        }
        println("Number of iterations: $count")
        return maxPressureRelieved
    }

    fun calculateMaxPressureRelief2(): Int {
        // the map of pressure relieve figures for each valve combination must have been updated
        return relievedPressureForValveCombiMap.keys.combinations(2).filter { Collections.disjoint(it[0], it[1]) }
            .maxOf {
                relievedPressureForValveCombiMap[it[0]]!! + relievedPressureForValveCombiMap[it[1]]!!
            }
    }

    private fun getRelievedPressureAtEnd(state: State, maxTime: Int): Int {
        val remainingTime = maxTime - state.elapsedTime
        val totalRate = valveMap.values.filter { state.openedValves.contains(it.id) }.sumOf { it.rate }
        return state.pressureRelieved + totalRate * remainingTime
    }
}

data class Valve(val id: String, val rate: Int)

data class State(val curValve: String, val openedValves: List<String> = listOf(),
                 val elapsedTime: Int = 0, val pressureRelieved: Int = 0)
